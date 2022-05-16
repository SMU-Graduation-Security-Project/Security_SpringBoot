package com.EmperorPenguin.SangmyungBank.user.dto;

import com.EmperorPenguin.SangmyungBank.user.entity.Role;
import com.EmperorPenguin.SangmyungBank.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserReq {

    @ApiModelProperty(required = true)
    private String loginId;
    @ApiModelProperty(required = true)
    private String email;
    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = true)
    private String password;
    @ApiModelProperty(required = true)
    private int age;
    @ApiModelProperty(required = true)
    private char sex;
    @ApiModelProperty(required = true)
    private String phoneNumber;

    public User toEntity(String encryptedPassword, Role defaultRole)
    {
        return User.builder()
                .loginId(loginId)
                .password(encryptedPassword)
                .email(email)
                .name(name)
                .age(age)
                .sex(sex)
                .phoneNumber(phoneNumber)
                .role(defaultRole)
                .build();
    }


}
