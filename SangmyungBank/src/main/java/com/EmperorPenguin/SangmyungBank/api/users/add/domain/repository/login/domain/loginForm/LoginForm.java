package com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.login.domain.loginForm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginForm {

    private String loginId;
    private String rawPassword;

}
