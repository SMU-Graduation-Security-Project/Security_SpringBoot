package com.EmperorPenguin.SangmyungBank.transaction.repository;

import com.EmperorPenguin.SangmyungBank.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.sendAccount = ?1 and t.transactionDate >= ?2 and t.transactionDate <= ?3")
    List<Transaction> getAllBySendAccount(@Param("sendAccount") Long sendAccount, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
