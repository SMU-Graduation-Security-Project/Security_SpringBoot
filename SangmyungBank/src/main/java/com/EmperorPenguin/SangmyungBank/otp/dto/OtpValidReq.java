package com.EmperorPenguin.SangmyungBank.otp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OtpValidReq {

    private String loginId;
    private int pkOTP4;
    private int selectNum1;
    private int selectNum2;
    private int ansNum1;
    private int ansNum2;
}
