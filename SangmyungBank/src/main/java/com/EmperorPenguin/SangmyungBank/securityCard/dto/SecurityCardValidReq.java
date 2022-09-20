package com.EmperorPenguin.SangmyungBank.securityCard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SecurityCardValidReq {

    private String loginId;
    private String hashedData;
}
