package com.EmperorPenguin.SangmyungBank.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberInquiryRes {
    private String loginId;
    private String email;
    private String name;
    private int age;
    private String sex;
    private String phoneNumber;
    private String loginDate;
    private String modifyDate;
}
