package com.EmperorPenguin.SangmyungBank.api.user.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

    @Column(columnDefinition = "text", unique = true,nullable = false)
    @NotEmpty
    private String email;

    @Column(columnDefinition = "int", nullable = false)
    @NotEmpty
    private int age;

    @Column(length = 1, nullable = false)
    @NotEmpty
    private char sex;

    @Column(length = 11, unique = true, nullable = false)
    @NotEmpty
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String loginId, String password, String name, String email, int age, char sex, String phoneNumber ,Role role)
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




