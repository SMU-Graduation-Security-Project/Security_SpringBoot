package com.EmperorPenguin.SangmyungBank.otp.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.BaseException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.otp.dto.OtpRandomRes;
import com.EmperorPenguin.SangmyungBank.otp.dto.OtpValidReq;
import com.EmperorPenguin.SangmyungBank.otp.entity.Otp;
import com.EmperorPenguin.SangmyungBank.otp.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.security.MessageDigest;

@Service
@RequiredArgsConstructor
public class OtpService {

    long currentTme = System.currentTimeMillis();
    SimpleDateFormat timeFormat=new SimpleDateFormat("yyyyMMdd");
    String ToHour=timeFormat.format(new Date(currentTme));

    private final OtpRepository otpRepository;
    private final  long Seed = Long.parseLong(ToHour);
    private final Random random = new Random(Seed);


    // 고정된 OTP 번호를 삽입.
    @Transactional
    public void createOtp(Member member) {
        try {
            Otp MemberOtp = Otp.builder()
                    .otpPrivateNumber(19741201)
                    .memberId(member)
                    .number1(4228)
                    .number2(6973)
                    .number3(1011)
                    .number4(9253)
                    .number5(1820)
                    .number6(3563)
                    .number7(2498)
                    .number8(1662)
                    .number9(2890)
                    .number10(2339)
                    .number11(1077)
                    .number12(3840)
                    .build();
            otpRepository.save(MemberOtp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("Otp 생성에 실패했습니다.");
        }
    }

    @Transactional
    public void validationOtp(Member member, OtpRandomRes otpRandomRes, String hashedData) throws NoSuchAlgorithmException {
        Otp otp = otpRepository.findByMemberId(member)
                .orElseThrow(() -> new BaseException(ExceptionMessages.ERROR_OTP_NOT_EXIST));

        // 앞의 2자리
        int num1 = otp.getOtpNumber(otpRandomRes.getSelect1()) / 100;
        // 뒤의 2자리
        int num2 = otp.getOtpNumber(otpRandomRes.getSelect2()) % 100;
        // 뒤의 4자리
        int pk4 = otp.getOtpPrivateNumber() % 1000;

        String data = String.format("%d%d%d",pk4,num1,num2);
        String hashData = hashingData(data);
        if(!hashData.equals(hashedData))
            throw new BaseException(ExceptionMessages.ERROR_OTP_NOT_MATCH);
    }

//    @Transactional
//    public OtpRequestRes getOtpData(String loginId) {
//        return otpRepository.findByMemberId(memberService.getMember(loginId)).get().toDto();
//    }

    public OtpRandomRes selectNumber(){
        OtpRandomRes otpRandomRes = new OtpRandomRes();
        while(true) {
            otpRandomRes.setSelect1(random.nextInt(12));
            otpRandomRes.setSelect2(random.nextInt(12));
            if(otpRandomRes.checkNumber())
                break;;
        }
        return otpRandomRes;
    }

    private String hashingData(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes){
            builder.append(String.format("%02x",b));
        }
        return builder.toString();
    }
}



