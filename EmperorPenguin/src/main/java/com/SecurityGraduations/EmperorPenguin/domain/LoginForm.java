package com.SecurityGraduations.EmperorPenguin.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {
    @NotEmpty
    private String loginID;

    @NotEmpty
    private String password;
}
