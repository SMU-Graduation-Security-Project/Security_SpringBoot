package com.SecurityGraduations.EmperorPenguin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDentityNum;

    @Column(columnDefinition = "text", nullable = false)
    private String ID;

    @Column(columnDefinition = "text", nullable = false)
    private String Email;

    @Column(columnDefinition = "text", nullable = false)
    private String Password;

    @Builder
    public User(String ID, String Email, String Password, String salt)
    {
        this.ID = ID;
        this.Email = Email;
        this.Password = Password;
    }
}
