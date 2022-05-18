package com.EmperorPenguin.SangmyungBank.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserLoginRes {
    private String loginId;
    private String name;
    private String loginDate;
}