package com.EmperorPenguin.SangmyungBank.loan.dto;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.card.entity.Card;
import com.EmperorPenguin.SangmyungBank.loan.entity.Loan;
import com.EmperorPenguin.SangmyungBank.loanlist.entity.LoanList;
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
public class LoanCreateReq {

    @ApiModelProperty(required = true)
    private String loginId;

    @ApiModelProperty(required = true)
    private Long accountNumber;

    @ApiModelProperty(required = true)
    private Long loanList;

    @ApiModelProperty(required = true)
    private Long amount;

    public Loan toEntity(Member member, Account account, LoanList loanList) {
        return Loan.builder()
                .memberId(member)
                .accountNumber(account)
                .loanList(loanList)
                .amount(amount)
                .build();
    }
}
