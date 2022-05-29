package com.EmperorPenguin.SangmyungBank.member.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.UserException;
import com.EmperorPenguin.SangmyungBank.member.dto.*;
import com.EmperorPenguin.SangmyungBank.member.entity.Role;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Random;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @Transactional
    public void register(MemberRegisterReq memberRegisterRequest) {
        String loginId = memberRegisterRequest.getLoginId();
        String email = memberRegisterRequest.getEmail();
        String phoneNumber = memberRegisterRequest.getPhoneNumber();
        String password1 = memberRegisterRequest.getPassword1();
        String password2 = memberRegisterRequest.getPassword2();

        if(memberRepository.findByLoginId(loginId).isPresent()){
            throw new UserException(ExceptionMessages.ERROR_MEMBER_ID_DUPLICATE);
        }

        // 사용자의 아이디와 비밀번호 검증
        checkLoginId(loginId);
        checkUserPassword(password1,password2);

        if(memberRepository.findByEmail(email).isPresent()){
            throw new UserException(ExceptionMessages.ERROR_MEMBER_EMAIL_DUPLICATE);
        }
        if(memberRepository.findByPhoneNumber(phoneNumber).isPresent()){
            throw new UserException(ExceptionMessages.ERROR_MEMBER_PHONENUMBER_DUPLICATE);
        }
        else {
            try{
                memberRepository.save(memberRegisterRequest.toEntity(passwordEncoder.encode(password1), Role.USER));
            }catch (Exception e){
                // Exception 이 발생한 이유와 위치는 어디에서 발생했는지 전체적인 단계를 다 출력합니다.
                e.printStackTrace();
                throw new UserException("회원가입에 실패했습니다.");
            }
        }
    }

    @Transactional
    public Member login(MemberLoginReq memberLoginReq)
    {
        Member member = memberRepository
                .findByLoginId(memberLoginReq.getLoginId())
                .orElseThrow(() -> new UserException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND));
        if (!passwordEncoder.matches(memberLoginReq.getPassword(), member.getPassword())){
            throw new UserException(ExceptionMessages.ERROR_MEMBER_PASSWORD);
        }

        memberRepository.updateLoginDate(new DateConfig().getDateTime(), memberLoginReq.getLoginId());
        // HttpSession Or JWT 도입예정.

        // 임시적으로 세션을 등록
        httpSession.setAttribute("user", new MemberSessionDto(member));

        return memberRepository
                .findByLoginId(memberLoginReq.getLoginId())
                .orElseThrow(() -> new UserException(ExceptionMessages.ERROR_UNDEFINED));
    }

    @Transactional
    public String setTemplatePassword(MemberFindPasswordReq memberFindPasswordReq){
        Member member = memberRepository
                .findByLoginId(memberFindPasswordReq.getLoginId())
                .orElseThrow(() -> new UserException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND));

        // 사용자 질문에 대한 검증
        if (!member.getQuestion().equals(memberFindPasswordReq.getQuestion())){
            throw new UserException(ExceptionMessages.ERROR_MEMBER_QUESTION_NOT_MATCH);
        }
        // 사용자 질문에 대한 답을 검증
        if(!member.getAnsWord().equals(memberFindPasswordReq.getAnsWord())){
            throw new UserException(ExceptionMessages.ERROR_MEMBER_ANSWORD_NOT_MATCH);
        }
        try {
            String templatePassword = randomNumberGen();
            memberRepository.updateUserTemplatePassword(
                    passwordEncoder.encode(templatePassword),
                    member.getUserId()
            );
            memberRepository.updateUserModifyDate(
                    new DateConfig().getDateTime(),
                    member.getUserId()
            );
            return templatePassword;
        }catch (Exception e){
            e.printStackTrace();
            throw new UserException("임시비밀번호로 변경이 실패했습니다.");
        }
    }

    @Transactional
    public void updateNewPassword(MemberPasswordUpdateReq memberPasswordUpdateReq){
        Member member = memberRepository
                .findByLoginId(memberPasswordUpdateReq.getLoginId())
                .orElseThrow(() -> new UserException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND));

        // 사용자가 임시 비밀번호를 사용중인지 확인
        if(!member.isUsingTempPassword()){
            throw new UserException("사용자는 임시비밀번호를 사용중이 아닙니다.");
        }

        // 사용자의 임시 비밀번호가 맞는지 확인.
        if(!passwordEncoder.matches(memberPasswordUpdateReq.getOldPassword(), member.getPassword())){
            throw new UserException(ExceptionMessages.ERROR_MEMBER_PASSWORD);
        }
        // 입력한 password가 규칙에 맞는지 확인.
        checkUserPassword(memberPasswordUpdateReq.getNewPassword1(), memberPasswordUpdateReq.getNewPassword2());
        try {
            memberRepository.updateUserPassword(
                    passwordEncoder.encode(memberPasswordUpdateReq.getNewPassword1()),
                    member.getUserId());
            memberRepository.updateUserModifyDate(
                    new DateConfig().getDateTime(),
                    member.getUserId()
            );
        }catch (Exception e){
            e.printStackTrace();
            throw new UserException("비밀번호 변경 실패");
        }
    }

    private void checkUserPassword(String password1, String password2) {
        // Password 규칙은 영문자, 특수문자를 포함 8~20이하이다.
        Pattern passwordExpression = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        if (!passwordExpression.matcher(password1).matches()) {
            throw new UserException(ExceptionMessages.ERROR_MEMBER_PASSWORD_FORMAT);
        } else if (!password1.equals(password2)) {
            throw new UserException("입력한 비밀번호가 서로 다릅니다.");
        }
    }
    private void checkLoginId(String loginId) {
        // 시작은 영문으로만,{영문, 숫자} 으로만 이루어진 5 ~ 12자 이하이다.
        Pattern nameExpression = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{4,11}$");
        if (!nameExpression.matcher(loginId).matches()) {
            throw new UserException(ExceptionMessages.ERROR_MEMBER_ID_FORMAT);
        }
    }

    private String randomNumberGen(){
        int lefLimit = 48;              // '0'
        int rightLimit = 122;           // 'z'
        int targetStringLength = 15;    // 임의로 생성할 Password의 길이
        Random random = new Random();

        return random.ints(lefLimit,rightLimit +1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
