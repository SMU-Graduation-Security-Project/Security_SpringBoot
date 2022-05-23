package com.EmperorPenguin.SangmyungBank.transaction.repository;

import com.EmperorPenguin.SangmyungBank.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
