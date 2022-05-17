package com.EmperorPenguin.SangmyungBank.user.entity;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.user.dto.UserRegisterReq;
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

//    public UserRegisterReq toDto(){
//        return UserRegisterReq.builder()
//                .loginId(loginId)
//                .email(email)
//                .name(name)
//                .age(age)
//                .sex(sex)
//                .phoneNumber(phoneNumber)
//                .build();
//    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}




