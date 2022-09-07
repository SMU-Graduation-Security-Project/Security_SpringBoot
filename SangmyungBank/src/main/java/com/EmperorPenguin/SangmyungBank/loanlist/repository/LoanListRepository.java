package com.EmperorPenguin.SangmyungBank.loanlist.repository;

import com.EmperorPenguin.SangmyungBank.cardlist.entity.CardList;
import com.EmperorPenguin.SangmyungBank.loanlist.entity.LoanList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoanListRepository extends JpaRepository<LoanList, Long> {
    Optional<LoanList> findByTitle(String title);

    @Modifying(clearAutomatically = true)
    @Query("update LoanList n set n.title = ?2, n.interestRate = ?3, n.interestType = ?4 where n.id = ?1")
    void updateLoanList(@Param("id")Long id, @Param("title") String title, @Param("interestRate") String interestRate, @Param("interestType") String interestType);
}
