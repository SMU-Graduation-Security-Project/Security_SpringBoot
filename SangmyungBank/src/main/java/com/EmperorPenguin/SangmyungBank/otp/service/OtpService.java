package com.EmperorPenguin.SangmyungBank.otp.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.BaseException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.otp.dto.OtpRequestRes;
import com.EmperorPenguin.SangmyungBank.otp.dto.OtpValidReq;
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
            throw new BaseException(ExceptionMessages.ERROR_OTP_EXIST);
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
            throw new BaseException("Otp 생성에 실패했습니다.");
        }
    }

    @Transactional
    public OtpRequestRes getOtpData(String loginId) {
        memberService.checkEmptyMember(loginId);

        return otpRepository.findByMemberId(memberService.getMember(loginId)).get().toDto();
    }

    @Transactional
    public void validationOtp(OtpValidReq otpValidReq){
        String loginId = otpValidReq.getLoginId();
        memberService.checkEmptyMember(loginId);

        Otp memberOtp = otpRepository.findByMemberId(memberService.getMember(loginId))
                .orElseThrow(() -> new BaseException(ExceptionMessages.ERROR_OTP_NOT_EXIST));

        checkOTP(memberOtp, otpValidReq);
    }

    private void checkOTP(Otp otp, OtpValidReq otpValidReq) {
        int Pk4 = (otp.getOtpPrivateNumber() % 10000);
        int[] otpNumList = new int[6];

        if(Pk4 != otpValidReq.getPkOTP4()){
            throw new BaseException(ExceptionMessages.ERROR_OTP_PK_NOT_MATCH);
        }

        // OTP값을 검증하기 위해 배열에 저장.
        otpNumList[0] = otp.getNumber1();
        otpNumList[1] = otp.getNumber2();
        otpNumList[2] = otp.getNumber3();
        otpNumList[3] = otp.getNumber4();
        otpNumList[4] = otp.getNumber5();
        otpNumList[5] = otp.getNumber6();

        if((otpNumList[otpValidReq.getSelectNum1()-1] / 100) != otpValidReq.getAnsNum1()){
            throw new BaseException(ExceptionMessages.ERROR_OTP_NOT_MATCH);
        }
        if ((otpNumList[otpValidReq.getSelectNum2()-1] % 100) != otpValidReq.getAnsNum2()){
            throw new BaseException(ExceptionMessages.ERROR_OTP_NOT_MATCH);
        }
    }
}



