package com.SecurityGraduations.EmperorPenguin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    @Column(columnDefinition = "text", nullable = false)
    private String id;

    private String email;

    private String password;

    @Builder
    public User(String id, String email, String password)
    {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
