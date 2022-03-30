package com.SecurityGraduations.EmperorPenguin.PasswordLogin.domain;

import com.SecurityGraduations.EmperorPenguin.Common.domain.Role;
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
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
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

    public String getRoleKey(){
        return this.role.getKey();
    }
}
