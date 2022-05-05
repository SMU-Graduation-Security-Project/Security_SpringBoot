package com.EmperorPenguin.SangmyungBank.api.users.accounts.add.domain.accountForm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class AccountForm {

    private String loginId;
    private String accountPassword;

    @Builder
    public AccountForm(String loginId, String accountPassword) {
        this.loginId = loginId;
        this.accountPassword = accountPassword;
    }
}
