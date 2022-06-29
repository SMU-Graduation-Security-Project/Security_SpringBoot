package com.EmperorPenguin.SangmyungBank.otp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OtpRequestRes {

    private int otpNumber;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;

}

