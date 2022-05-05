package com.EmperorPenguin.SangmyungBank.api.users.accounts.transaction.domain.transactionForm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class TransactionForm {

    private Long myAccountNumber;
    private Long sendAccountNumber;
    private String accountPassword;
    private Long balance;

    @Builder
    public TransactionForm(Long myAccountNumber, Long sendAccountNumber ,String accountPassword, Long balance)
    {
        this.myAccountNumber = myAccountNumber;
        this.sendAccountNumber = sendAccountNumber;
        this.accountPassword = accountPassword;
        this.balance = balance;
    }

}
