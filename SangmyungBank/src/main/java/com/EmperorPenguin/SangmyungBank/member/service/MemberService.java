package com.EmperorPenguin.SangmyungBank.member.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.MemberException;
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
    public Member login(MemberLoginReq memberLoginReq)
    {
        Member member = getMember(memberLoginReq.getLoginId());

        if (!passwordEncoder.matches(memberLoginReq.getPassword(), member.getPassword())){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_PASSWORD);
        }

        memberRepository.updateLoginDate(new DateConfig().getDateTime(), memberLoginReq.getLoginId());
        // HttpSession Or JWT 도입예정.

        // 임시적으로 세션을 등록
        httpSession.setAttribute("user", new MemberSessionDto(member));

        return memberRepository
                .findByLoginId(memberLoginReq.getLoginId())
                .orElseThrow(() -> new MemberException(ExceptionMessages.ERROR_UNDEFINED));
    }

    // 로그아웃 부분

    @Transactional
    public void register(MemberRegisterReq memberRegisterRequest) {
        String loginId = memberRegisterRequest.getLoginId();
        String email = memberRegisterRequest.getEmail();
        String phoneNumber = memberRegisterRequest.getPhoneNumber();
        String password1 = memberRegisterRequest.getPassword1();
        String password2 = memberRegisterRequest.getPassword2();

        // 사용자의 아이디와 비밀번호 형식 검증
        checkLoginId(loginId);
        checkPassword(password1,password2);
        // 이미 있는 사용자인지 검증
        checkMember(loginId);

        if(memberRepository.findByEmail(email).isPresent()){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_EMAIL_DUPLICATE);
        }

        if(memberRepository.findByPhoneNumber(phoneNumber).isPresent()){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_PHONENUMBER_DUPLICATE);
        }

        else {
            try{
                memberRepository.save(memberRegisterRequest.toEntity(passwordEncoder.encode(password1), Role.USER));
            }catch (Exception e){
                // Exception 이 발생한 이유와 위치는 어디에서 발생했는지 전체적인 단계를 다 출력합니다.
                e.printStackTrace();
                throw new MemberException("회원가입에 실패했습니다.");
            }
        }
    }

    @Transactional
    public String setTemplatePassword(MemberFindPasswordReq memberFindPasswordReq){
        Member member = getMember(memberFindPasswordReq.getLoginId());

        // 사용자 질문에 대한 검증
        if (!member.getQuestion().equals(memberFindPasswordReq.getQuestion())){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_QUESTION_NOT_MATCH);
        }
        // 사용자 질문에 대한 답을 검증
        if(!member.getAnsWord().equals(memberFindPasswordReq.getAnsWord())){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_ANSWORD_NOT_MATCH);
        }
        try {
            String templatePassword = randomNumberGen();
            memberRepository.updateUserTemplatePassword(
                    passwordEncoder.encode(templatePassword),
                    member.getMemberId()
            );
            memberRepository.updateUserModifyDate(
                    new DateConfig().getDateTime(),
                    member.getMemberId()
            );
            return templatePassword;
        }catch (Exception e){
            e.printStackTrace();
            throw new MemberException("임시비밀번호로 변경이 실패했습니다.");
        }
    }

    @Transactional
    public void updateTemplatePassword(MemberPasswordUpdateReq memberPasswordUpdateReq){
        Member member = memberRepository
                .findByLoginId(memberPasswordUpdateReq.getLoginId())
                .orElseThrow(() -> new MemberException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND));

        // 사용자가 임시 비밀번호를 사용중인지 확인
        if(!member.isUsingTempPassword()){
            throw new MemberException("사용자는 임시비밀번호를 사용중이 아닙니다.");
        }

        // 사용자의 임시 비밀번호가 맞는지 확인.
        if(!passwordEncoder.matches(memberPasswordUpdateReq.getOldPassword(), member.getPassword())){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_PASSWORD);
        }

        // 입력한 password가 규칙에 맞는지 확인.
        checkPassword(memberPasswordUpdateReq.getNewPassword1(), memberPasswordUpdateReq.getNewPassword2());
        try {
            memberRepository.updateUserPassword(
                    passwordEncoder.encode(memberPasswordUpdateReq.getNewPassword1()),
                    member.getMemberId());
            memberRepository.updateUserModifyDate(
                    new DateConfig().getDateTime(),
                    member.getMemberId()
            );
        }catch (Exception e){
            e.printStackTrace();
            throw new MemberException("비밀번호 변경 실패");
        }
    }

    @Transactional
    public void updateNewPassword(MemberPasswordUpdateReq memberPasswordUpdateReq){
        Member member = getMember(memberPasswordUpdateReq.getLoginId());

        // 사용자의 현재 비밀번호가 맞는지 확인.
        if(!passwordEncoder.matches(memberPasswordUpdateReq.getOldPassword(), member.getPassword())){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_PASSWORD);
        }
        // 입력한 password가 규칙에 맞는지 확인.
        checkPassword(memberPasswordUpdateReq.getNewPassword1(), memberPasswordUpdateReq.getNewPassword2());
        try {
            memberRepository.updateUserPassword(
                    passwordEncoder.encode(memberPasswordUpdateReq.getNewPassword1()),
                    member.getMemberId());
            memberRepository.updateUserModifyDate(
                    new DateConfig().getDateTime(),
                    member.getMemberId()
            );
        }catch (Exception e){
            e.printStackTrace();
            throw new MemberException("비밀번호 변경 실패");
        }
    }

    public MemberInquiryRes getMemberData(String loginId){
        if(!memberRepository.existsByLoginId(loginId)){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND);
        }
        return memberRepository.findByLoginId(loginId).get().toDto();
    }

    private void checkLoginId(String loginId) {
        // 시작은 영문으로만,{영문, 숫자} 으로만 이루어진 5 ~ 12자 이하이다.
        Pattern nameExpression = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{4,11}$");
        if (!nameExpression.matcher(loginId).matches()) {
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_ID_FORMAT);
        }
    }

    private void checkPassword(String password1, String password2) {
        // Password 규칙은 영문자, 특수문자를 포함 8~20이하이다.
        Pattern passwordExpression = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        if (!passwordExpression.matcher(password1).matches()) {
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_PASSWORD_FORMAT);
        } else if (!password1.equals(password2)) {
            throw new MemberException("입력한 비밀번호가 서로 다릅니다.");
        }
    }

    public void checkMember(String loginId){
        if(memberRepository.findByLoginId(loginId).isEmpty()){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND);
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

    public Member getMember(String loginId){
        return memberRepository.findByLoginId(loginId)
                .orElseThrow(() -> new MemberException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND));
    }

}
