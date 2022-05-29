package com.EmperorPenguin.SangmyungBank.member.dto;

import com.EmperorPenguin.SangmyungBank.member.entity.Member;

import java.io.Serializable;

public class MemberSessionDto implements Serializable {
    private String userId;
    private String password;

    public MemberSessionDto(Member member){
        this.userId = member.getLoginId();
        this.password = member.getPassword();
    }
}
