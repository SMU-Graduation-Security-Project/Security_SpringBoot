package com.EmperorPenguin.SangmyungBank.api.users.account.service;

import com.EmperorPenguin.SangmyungBank.api.users.account.domain.account.Account;
import com.EmperorPenguin.SangmyungBank.api.users.account.domain.accountForm.AccountForm;
import com.EmperorPenguin.SangmyungBank.api.users.account.domain.repository.AccountAddRepository;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.login.domain.repository.UserLoginRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class AccountAddService {

    private final AccountAddRepository accountAddRepository;
    private final UserLoginRepository userLoginRepository;

    public AccountAddService(AccountAddRepository accountAddRepository, UserLoginRepository userLoginRepository){
        this.accountAddRepository = accountAddRepository;
        this.userLoginRepository = userLoginRepository;
    }

//Id가 DB에 존재하면,계좌발행
    public Account register(AccountForm accountForm){
        User dbUser = userLoginRepository.findByLoginId(accountForm.getLoginId()).get();
        if(dbUser == null)
            return null;
        else {
            Account account = Account.builder()
                    .userId(dbUser)
                    .accountPassword(accountForm.getAccountPassword())
                    .balance(0L)
                    .build();
           Account result = accountAddRepository.save(account);
           return result;
        }
    }
}
