package com.EmperorPenguin.SangmyungBank.member.entity;

import com.EmperorPenguin.SangmyungBank.member.dto.MemberLoginRes;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberInquiryRes;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true, nullable = false)
    private String loginId;
    private String email;
    @Column(nullable = false)
    private String name;
    private String password;

    @Column(nullable = false)
    private int age;

    @Column(length = 1, nullable = false)
    private String sex;

    @Column(length = 14, unique = true, nullable = false)
    private String phoneNumber;

    @Column
    private String loginDate;

    @Column
    private String registerDate;

    @Column
    private String modifyDate;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String ansWord;

    @Column(nullable = false)
    private boolean usingTempPassword;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public MemberLoginRes toLoginDto() {
        return MemberLoginRes.builder()
                .loginId(loginId)
                .name(name)
                .loginDate(loginDate)
                .usingTempPassword(usingTempPassword)
                .build();
    }

    public MemberInquiryRes toDto() {
        return MemberInquiryRes.builder()
                .loginId(loginId)
                .email(email)
                .name(name)
                .age(age)
                .sex(sex)
                .phoneNumber(phoneNumber)
                .loginDate(loginDate)
                .modifyDate(modifyDate)
                .build();
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public List<String> getRoleList(){
        if(this.role.getKey().length() > 0){
            return Arrays.asList(this.role.getKey().split(","));
        }
        return new ArrayList<>();
    }



}





