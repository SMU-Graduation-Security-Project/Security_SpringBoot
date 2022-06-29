package com.EmperorPenguin.SangmyungBank.otp.dto;
import com.EmperorPenguin.SangmyungBank.otp.service.OtpService;
import com.EmperorPenguin.SangmyungBank.otp.entity.Otp;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.Random;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

public class OtpCreateReq {
    Random random = new Random();
    int bound = 9000;

    @ApiModelProperty(required = true)
    private String loginId;

    public Otp toEntity(Member member) {
        return Otp.builder()
                .memberId(member)
                .otpNumber((getRandom().nextInt(bound+89991000))+10000000)
                .number1((getRandom().nextInt(bound))+1000)
                .number2((getRandom().nextInt(bound))+1000)
                .number3((getRandom().nextInt(bound))+1000)
                .number4((getRandom().nextInt(bound))+1000)
                .number5((getRandom().nextInt(bound))+1000)
                .build();
    }

}