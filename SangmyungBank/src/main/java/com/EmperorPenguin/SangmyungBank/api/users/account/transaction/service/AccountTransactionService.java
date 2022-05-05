package com.EmperorPenguin.SangmyungBank.api.users.account.transaction.service;

import com.EmperorPenguin.SangmyungBank.api.users.account.add.domain.account.Account;

import com.EmperorPenguin.SangmyungBank.api.users.account.transaction.domain.transactionForm.TransactionForm;
import com.EmperorPenguin.SangmyungBank.api.users.account.transaction.domain.repository.AccountTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountTransactionService {

    private final AccountTransactionRepository accountTransactionRepository;


    public boolean transaction (TransactionForm accountForm)
    {
        Account dbAccount = accountTransactionRepository.findByAccountNumber(accountForm.getMyAccountNumber()).orElse(null);
        if(dbAccount == null)
            return false;
        else
            if (dbAccount.getAccountPassword().equals(accountForm.getAccountPassword()))
                if ( dbAccount.getBalance() >= accountForm.getBalance())
                {
                    accountTransactionRepository.sendBalance(accountForm);
                    return true;
                }
                else
                    return false;
            else
                return false;

    }
}