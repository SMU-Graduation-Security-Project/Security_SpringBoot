package com.EmperorPenguin.SangmyungBank.account;

import com.EmperorPenguin.SangmyungBank.account.dto.AccountCreateReq;
import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.account.repository.AccountRepository;
import com.EmperorPenguin.SangmyungBank.account.service.AccountService;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.AccountException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.transaction.Transactional;

@Transactional
@SpringBootTest
public class accountTest {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Test
    void 계좌생성(){
        // given
        String loginId = "test1";
        String password = "123456";

        // when
        AccountCreateReq accountCreateReq = AccountCreateReq.builder()
                .loginId(loginId)
                .accountPassword(password)
                .build();

        accountService.createAccount(accountCreateReq);

        // then
        if(accountRepository.findAllByUserId(userRepository.findByLoginId(loginId).get()).isEmpty()){
            throw new AccountException(ExceptionMessages.ERROR_ACCOUNT_NOT_FOUND);
        }
    }

    @Test
    void 계좌이체(){
        Account account1= new Account();
        account1.setAccountNumber(1L);
        account1.setAccountPassword("1111");
        account1.setBalance(1000L);
        AccountRepository.save(account1);


        Account account2= new Account();
        account2.setUserId();
        account2.setAccountNumber();
        account2.setAccountPassword();
        account2.setBalance();
        AccountRepository.save(account2);



    }

}
