package com.EmperorPenguin.SangmyungBank.card.entity;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.card.dto.CardRequestRes;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    private Long cardNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="accountNumber")
    private Account accountNumber;

    @Column(nullable = false)
    private String cardType;

    @Column
    private String expireDate;

    public CardRequestRes toDto(){
        return CardRequestRes.builder()
                .cardNumber(cardNumber)
                .accountNumber(accountNumber.getAccountNumber())
                .cardType(cardType)
                .expireDate(expireDate)
                .build();
    }
}
