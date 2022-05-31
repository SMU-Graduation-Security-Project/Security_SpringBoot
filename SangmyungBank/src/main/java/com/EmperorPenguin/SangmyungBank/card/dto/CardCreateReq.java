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

    @ApiModelProperty(required = true)
    private String loginId;

    @ApiModelProperty(required = true)
    private Long accountNumber;

    @ApiModelProperty(required = true)
    private String cardType;

    public Card toEntity(Member member, Account account) {
        return Card.builder()
                .memberId(member)
                .accountNumber(account)
                .cardType(cardType)
                .expireDate(new DateConfig().getExpireDate())
                .build();
    }

}
