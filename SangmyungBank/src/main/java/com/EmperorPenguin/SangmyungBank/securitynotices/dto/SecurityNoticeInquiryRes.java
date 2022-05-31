package com.EmperorPenguin.SangmyungBank.securitynotices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SecurityNoticeInquiryRes {

    private Long id;
    private String title;
    private String content;
    private String createdDate;
}
