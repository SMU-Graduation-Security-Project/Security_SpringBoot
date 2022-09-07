package com.EmperorPenguin.SangmyungBank.member.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberLoginRes {
    private String loginId;
    private String name;
    private String loginDate;
    private boolean usingTempPassword;
}