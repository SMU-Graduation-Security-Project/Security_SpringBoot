package com.EmperorPenguin.SangmyungBank.api.users.login.domain.loginForm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class LoginForm {

    private String loginId;
    private String rawPassword;

    @Builder
    public LoginForm(String loginId, String password)
    {
        this.loginId = loginId;
        this.rawPassword = password;
    }
}
