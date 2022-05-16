package com.EmperorPenguin.SangmyungBank.user.entity;

import com.EmperorPenguin.SangmyungBank.user.dto.UserRes;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(columnDefinition = "text", unique = true, nullable = false)
    private String loginId;
    private String email;
    @Column(columnDefinition = "text", nullable = false)
    private String name;
    private String password;

    @Column(columnDefinition = "integer", nullable = false)
    private int age;

    @Column(length = 2, nullable = false)
    private char sex;

    @Column(length = 15, unique = true, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String loginDate;

    @Column
    private String registerDate;

    @Column
    private String modifyDate;

    public UserRes toDto(){
        return UserRes.builder()
                .userId(userId)
                .loginId(loginId)
                .name(name)
                .phoneNumber(phoneNumber)
                .loginDate(loginDate)
                .modifyDate(modifyDate)
                .role(role)
                .build();
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}




