package com.EmperorPenguin.SangmyungBank.otp.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.OtpException;
import com.EmperorPenguin.SangmyungBank.otp.dto.OtpRequestRes;
import com.EmperorPenguin.SangmyungBank.otp.entity.Otp;
import com.EmperorPenguin.SangmyungBank.otp.repository.OtpRepository;
import com.EmperorPenguin.SangmyungBank.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;
    private final MemberService memberService;
    private final long Seed = System.currentTimeMillis();
    private final Random random = new Random(Seed);

    @Transactional
    public void createOtp(String loginId) {
        // 사용자가 있는지 검증한다.
        memberService.checkEmptyMember(loginId);

        // OTP를 발급한 적이 있는지 확인한다.
        if(otpRepository.findByMemberId(memberService.getMember(loginId)).isPresent()) {
            throw new OtpException(ExceptionMessages.ERROR_OTP_EXIST);
        }

        try {
            Otp MemberOtp = Otp.builder()
                    .otpPrivateNumber(random.nextInt(99999999))
                    .memberId(memberService.getMember(loginId))
                    .number1(random.nextInt(9999))
                    .number2(random.nextInt(9999))
                    .number3(random.nextInt(9999))
                    .number4(random.nextInt(9999))
                    .number5(random.nextInt(9999))
                    .number6(random.nextInt(9999))
                    .build();
            otpRepository.save(MemberOtp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new OtpException("Otp 생성에 실패했습니다.");
        }
    }

    @Transactional
    public OtpRequestRes getOtpData(String loginId) {
        memberService.checkEmptyMember(loginId);

        return otpRepository.findByMemberId(memberService.getMember(loginId)).get().toDto();
    }

}



