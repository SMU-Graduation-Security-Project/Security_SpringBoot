package com.EmperorPenguin.SangmyungBank.user.entity;

import com.EmperorPenguin.SangmyungBank.user.dto.UserLoginRes;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User{
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false)
    private String loginId;
    private String email;
    @Column(nullable = false)
    private String name;
    private String password;

    @Column(nullable = false)
    private int age;

    @Column(length = 2, nullable = false)
    private char sex;

    @Column(length = 15, unique = true, nullable = false)
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

    public UserLoginRes toDto(){
        return UserLoginRes.builder()
                .loginId(loginId)
                .name(name)
                .loginDate(loginDate)
                .usingTempPassword(usingTempPassword)
                .build();
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}




