package com.EmperorPenguin.SangmyungBank.user.dto;

import com.EmperorPenguin.SangmyungBank.user.entity.User;

import java.io.Serializable;

public class UserSessionDto implements Serializable {
    private String userId;
    private String password;

    public UserSessionDto(User user){
        this.userId = user.getLoginId();
        this.password = user.getPassword();
    }
}
