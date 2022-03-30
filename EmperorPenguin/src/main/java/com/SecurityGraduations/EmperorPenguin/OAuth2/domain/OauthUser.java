package com.SecurityGraduations.EmperorPenguin.OAuth2.domain;

import com.SecurityGraduations.EmperorPenguin.Common.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OauthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", nullable = false)
    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    @Builder
    public OauthUser(String name, String email, Role role)
    {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public OauthUser update(String name){
        this.name=name;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
