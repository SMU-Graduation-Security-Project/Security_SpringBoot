package com.EmperorPenguin.SangmyungBank.card.repository;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.card.entity.Card;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByMemberId(Member memberId);
}
