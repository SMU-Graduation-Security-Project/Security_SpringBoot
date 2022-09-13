package com.EmperorPenguin.SangmyungBank.card.dto;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.card.entity.Card;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CardCreateReq {

    @ApiModelProperty(required = true, value = "로그인ID",example = "test1234",position = 0)
    private String loginId;

    @ApiModelProperty(required = true, value = "계좌번호",example = "1111",position = 1)
    private Long accountNumber;

    @ApiModelProperty(required = true, value = "카드타입",example = "C",position = 2)
    private String cardType;

    public Card toEntity(Member member, Account account, Long cardNumber) {
        return Card.builder()
                .memberId(member)
                .accountNumber(account)
                .cardNumber(cardNumber)
                .cardType(cardType)
                .expireDate(new DateConfig().getExpireDate())
                .build();
    }

}
