package com.EmperorPenguin.SangmyungBank.api.users.account.transaction.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.users.account.add.domain.account.Account;
import com.EmperorPenguin.SangmyungBank.api.users.account.transaction.domain.transactionForm.TransactionForm;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountTransactionRepository {

    private final EntityManager em;

    public AccountTransactionRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<Account> findByAccountNumber(Long accountNumber)
    {
        List<Account> result = em.createQuery("select m from Account m where m.accountNumber = :accountNumber", Account.class)
                .setParameter("accountNumber",accountNumber)
                .getResultList();
        return result.stream().findAny();
    }

    public void updateMyBalance(TransactionForm transactionForm)
    {
        em.createQuery("update Account m set m.balance = m.balance - :balance where m.accountNumber =:accountNumber")
                .setParameter("balance", transactionForm.getBalance())
                .setParameter("accountNumber", transactionForm.getMyAccountNumber())
                .executeUpdate();
    }

    public void sendBalance(TransactionForm transactionForm)
    {
        em.createQuery("update Account m set m.balance = m.balance + :balance where m.accountNumber =:accountNumber")
                .setParameter("balance", transactionForm.getBalance())
                .setParameter("accountNumber", transactionForm.getSendAccountNumber())
                .executeUpdate();
    }
}
