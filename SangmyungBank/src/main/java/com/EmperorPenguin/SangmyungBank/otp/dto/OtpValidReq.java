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
    private String hashedData;
}
