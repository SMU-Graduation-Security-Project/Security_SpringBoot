package com.SecurityGraduations.EmperorPenguin.Login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    @Column(columnDefinition = "text", nullable = true)
    private String id;
    private String password;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String id, String password, String name, String email, Role role)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User update(String name){
        this.name=name;
        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }
}
