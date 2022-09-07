package com.EmperorPenguin.SangmyungBank.otp.entity;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.otp.dto.OtpRequestRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Otp {

    @Id
    private int otpPrivateNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member memberId;

    @Column
    private int number1;

    @Column
    private int number2;

    @Column
    private int number3;

    @Column
    private int number4;

    @Column
    private int number5;

    @Column
    private int number6;

    public OtpRequestRes toDto(){
        return OtpRequestRes.builder()
                .otpPrivateNumber(otpPrivateNumber)
                .number1(number1)
                .number2(number2)
                .number3(number3)
                .number4(number4)
                .number5(number5)
                .number6(number6)
                .build();
    }
}

