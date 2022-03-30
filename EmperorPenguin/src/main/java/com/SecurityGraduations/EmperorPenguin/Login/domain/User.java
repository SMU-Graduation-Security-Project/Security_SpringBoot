package com.SecurityGraduations.EmperorPenguin.Login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty
    private String id;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    @Builder
    public User(String id, String email, String password)
    {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
