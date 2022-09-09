package com.EmperorPenguin.SangmyungBank.loan.repository;

import com.EmperorPenguin.SangmyungBank.loan.entity.Loan;
import com.EmperorPenguin.SangmyungBank.loanlist.entity.LoanList;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByLoanList(LoanList loanList);

    List<Loan> findAllByMemberId(Member memberId);

    @Modifying(clearAutomatically = true)
    @Query("update Account a set a.balance = a.balance + ?1 where a.accountNumber = ?2")
    void updateBalance(Long balance,  Long AccountNumber);

}
