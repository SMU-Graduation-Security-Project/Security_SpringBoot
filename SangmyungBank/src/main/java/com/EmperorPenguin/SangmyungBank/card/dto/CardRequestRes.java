package com.EmperorPenguin.SangmyungBank.card.dto;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CardRequestRes {

    private Long cardNumber;
    private Long accountNumber;
    private String cardType;
    private String expireDate;
}
