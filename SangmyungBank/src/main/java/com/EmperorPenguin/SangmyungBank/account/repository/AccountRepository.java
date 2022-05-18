package com.EmperorPenguin.SangmyungBank.account.repository;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserId(User userId);

    Optional<Account> findAccountByAccountNumber(Long accountNumber);

    boolean existsAccountByAccountNumber(Long accountNumber);

    @Modifying()
    @Query("update Account a set a.balance = a.balance - ?1 where a.accountNumber = ?2")
    void updateMyBalance(Long balance, Long accountNumber);

    @Modifying()
    @Query("update Account a set a.balance = a.balance + ?1 where a.accountNumber = ?2")
    void updateBalance(Long balance,  Long sendAccountNumber);
}
