package com.EmperorPenguin.SangmyungBank.api.user.account.transaction;

import com.EmperorPenguin.SangmyungBank.api.users.accounts.transaction.domain.transactionForm.TransactionForm;
import com.EmperorPenguin.SangmyungBank.api.users.accounts.transaction.service.AccountTransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
public class transactionTest {
    @Autowired
    AccountTransactionService accountTransactionService;
    @Test
    void 계좌이체(){
        //given
        Long myAccountNum = 2L;
        String password = "1344";
        Long target = 1L;
        Long balance = 100L;

        // when
        TransactionForm transactionForm = TransactionForm.builder()
                .myAccountNumber(myAccountNum)
                .accountPassword(password)
                .sendAccountNumber(target)
                .balance(balance)
                .build();
        //then
        assertThat(true).isEqualTo(accountTransactionService.transaction(transactionForm));

    }

}
