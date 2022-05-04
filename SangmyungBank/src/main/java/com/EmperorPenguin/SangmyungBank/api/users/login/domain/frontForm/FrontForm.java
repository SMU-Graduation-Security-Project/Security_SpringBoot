package com.EmperorPenguin.SangmyungBank.api.users.login.domain.frontForm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class FrontForm {

    private String loginId;
    private String userName;

    @Builder
    public FrontForm(String loginId, String userName) {
        this.loginId = loginId;
        this.userName = userName;
    }
}