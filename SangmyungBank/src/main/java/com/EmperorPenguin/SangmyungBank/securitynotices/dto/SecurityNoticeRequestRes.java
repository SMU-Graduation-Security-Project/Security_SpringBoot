package com.EmperorPenguin.SangmyungBank.securitynotices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SecurityNoticeRequestRes {

    private String title;
    private String context;
    private String createdDate;
}
