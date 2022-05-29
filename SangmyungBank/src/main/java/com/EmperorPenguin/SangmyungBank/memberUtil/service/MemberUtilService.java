package com.EmperorPenguin.SangmyungBank.memberUtil.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.MemberException;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import com.EmperorPenguin.SangmyungBank.memberUtil.dto.MemberFindPasswordReq;
import com.EmperorPenguin.SangmyungBank.memberUtil.dto.MemberInquiryRes;
import com.EmperorPenguin.SangmyungBank.memberUtil.dto.MemberPasswordUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberUtilService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String setTemplatePassword(MemberFindPasswordReq memberFindPasswordReq){
        Member member = memberRepository
                .findByLoginId(memberFindPasswordReq.getLoginId())
                .orElseThrow(() -> new MemberException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND));

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
    public void updateNewPassword(MemberPasswordUpdateReq memberPasswordUpdateReq){
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
        checkMemberPassword(memberPasswordUpdateReq.getNewPassword1(), memberPasswordUpdateReq.getNewPassword2());
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

    private void checkMemberPassword(String password1, String password2) {
        // Password 규칙은 영문자, 특수문자를 포함 8~20이하이다.
        Pattern passwordExpression = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        if (!passwordExpression.matcher(password1).matches()) {
            throw new MemberException(ExceptionMessages.ERROR_MEMBER_PASSWORD_FORMAT);
        } else if (!password1.equals(password2)) {
            throw new MemberException("입력한 비밀번호가 서로 다릅니다.");
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
