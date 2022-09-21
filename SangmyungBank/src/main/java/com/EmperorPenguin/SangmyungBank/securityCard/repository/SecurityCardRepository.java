package com.EmperorPenguin.SangmyungBank.securityCard.repository;

import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.securityCard.entity.SecurityCard;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SecurityCardRepository extends JpaRepository<SecurityCard, Long> {
        Optional<SecurityCard> findByMemberId(Member loginUser);
}


