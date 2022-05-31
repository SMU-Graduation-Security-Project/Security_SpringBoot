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

        if (memberRegisterRequest.checkNull()){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_CREATE_FORM_HAS_NULL);
        }

        if(memberRepository.findByLoginId(loginId).isPresent()){
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_ID_DUPLICATE);
        }

        // 사용자의 아이디와 비밀번호 검증
        checkLoginId(loginId);
        checkMemberPassword(password1,password2);

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
    public Member login(MemberLoginReq memberLoginReq)
    {
        Member member = memberRepository
                .findByLoginId(memberLoginReq.getLoginId())
                .orElseThrow(() -> new MemberException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND));
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


    private void checkMemberPassword(String password1, String password2) {
        // Password 규칙은 영문자, 특수문자를 포함 8~20이하이다.
        Pattern passwordExpression = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        if (!passwordExpression.matcher(password1).matches()) {
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_PASSWORD_FORMAT);
        } else if (!password1.equals(password2)) {
            throw new MemberException("입력한 비밀번호가 서로 다릅니다.");
        }
    }
    private void checkLoginId(String loginId) {
        // 시작은 영문으로만,{영문, 숫자} 으로만 이루어진 5 ~ 12자 이하이다.
        Pattern nameExpression = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{4,11}$");
        if (!nameExpression.matcher(loginId).matches()) {
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_ID_FORMAT);
        }
    }


}
