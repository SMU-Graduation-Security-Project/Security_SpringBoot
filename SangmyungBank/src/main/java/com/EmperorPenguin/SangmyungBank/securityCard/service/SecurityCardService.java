package com.EmperorPenguin.SangmyungBank.securityCard.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.BaseException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.securityCard.dto.SecurityCardRandomRes;
import com.EmperorPenguin.SangmyungBank.securityCard.entity.SecurityCard;
import com.EmperorPenguin.SangmyungBank.securityCard.repository.SecurityCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.security.MessageDigest;

@Service
@RequiredArgsConstructor
public class SecurityCardService {

    private final SecurityCardRepository securityCardRepository;
    private final long Seed = Long.parseLong(new DateConfig().getSeed());
    private final Random random = new Random(Seed);

    // 고정된 OTP 번호를 삽입.
    @Transactional
    public void createSecurityCard(Member member) {
        try {
            SecurityCard MemberSecurityCard = SecurityCard.builder()
                    .securityCardPrivateNumber(19741201)
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
            securityCardRepository.save(MemberSecurityCard);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("보안카드 생성에 실패했습니다.");
        }
    }

    @Transactional
    public void validationSecurityCard(Member member, SecurityCardRandomRes securityCardRandomRes, String receivedData) throws NoSuchAlgorithmException {
        SecurityCard securityCard = securityCardRepository.findByMemberId(member)
                .orElseThrow(() -> new BaseException(ExceptionMessages.ERROR_SECURITYCARD_NOT_EXIST));

        // 앞의 2자리
        int num1 = securityCard.getSecurityCardNumber(securityCardRandomRes.getSelect1()) / 100;
        // 뒤의 2자리
        int num2 = securityCard.getSecurityCardNumber(securityCardRandomRes.getSelect2()) % 100;
//      뒤의 4자리
//        int pk4 = securityCard.getSecurityCardPrivateNumber() % 1000;

        String Data = member.getRefreshToken();
        String salt = String.format("%d%d",num1,num2);
        String hashData = hashingData(Data, salt);
        if(!hashData.substring(0,32).equals(receivedData))
            throw new BaseException(ExceptionMessages.ERROR_SECURITYCARD_NOT_MATCH);
    }

//    @Transactional
//    public OtpRequestRes getOtpData(String loginId) {
//        return otpRepository.findByMemberId(memberService.getMember(loginId)).get().toDto();
//    }

    public SecurityCardRandomRes selectNumber(){
        SecurityCardRandomRes securityCardRandomRes = new SecurityCardRandomRes();
        while(true) {
            securityCardRandomRes.setSelect1(random.nextInt(12));
            securityCardRandomRes.setSelect2(random.nextInt(12));
            if(securityCardRandomRes.checkNumber())
                break;;
        }
        return securityCardRandomRes;
    }

    private String hashingData(String data, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        md.update(salt.getBytes());
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



