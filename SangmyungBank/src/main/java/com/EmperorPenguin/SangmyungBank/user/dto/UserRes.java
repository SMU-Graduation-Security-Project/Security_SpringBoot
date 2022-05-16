package com.EmperorPenguin.SangmyungBank.user.dto;

import com.EmperorPenguin.SangmyungBank.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRes {
    private Long userId;
    private String loginId;
    private String name;
    private String phoneNumber;
    private String loginDate;
    private String modifyDate;
    private Role role;
}
