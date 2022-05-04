package com.EmperorPenguin.SangmyungBank.api.users.account.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.users.account.domain.account.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountAddRepository {

    private final EntityManager em;

    public AccountAddRepository(EntityManager em) {
        this.em = em;
    }

    public Account save(Account account){
        em.persist(account);
        return account;
    }

    public List<Account> findByUserId(String userId) {
        List<Account> userAccounts = em.createQuery("select m from Account m where m.userId =:userId",Account.class)
                .setParameter("userId", userId)
                .getResultList();
        return userAccounts;
    }

    public Optional<Account> findByAccountNumber(Long accountNumber) {
        List<Account> userAccount = em.createQuery("select m from Account m where m.accountNumber =:accountNumber",Account.class)
                .setParameter("accountNumber", accountNumber)
                .getResultList();
        return userAccount.stream().findAny();
    }
}
