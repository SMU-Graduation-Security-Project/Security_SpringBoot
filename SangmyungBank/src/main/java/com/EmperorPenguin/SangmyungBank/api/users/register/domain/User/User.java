package com.EmperorPenguin.SangmyungBank.api.users.register.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User extends BaseTimeEntity implements Serializable {
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

    @Builder
    public User(String loginId, String password, String name, String email, int age, char sex, String phoneNumber,Role role)
    {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }


    public String getRoleKey(){
        return this.role.getKey();
    }
}




