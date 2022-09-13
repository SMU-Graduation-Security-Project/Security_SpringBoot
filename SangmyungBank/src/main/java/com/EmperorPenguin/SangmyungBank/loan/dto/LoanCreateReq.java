package com.EmperorPenguin.SangmyungBank.loan.dto;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
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

    @ApiModelProperty(required = true, value = "로그인ID",example = "test1234",position = 0)
    private String loginId;

    @ApiModelProperty(required = true, value = "계좌번호",example = "1111",position = 1)
    private Long accountNumber;

    @ApiModelProperty(required = true, value = "대출종류",example = "학자금대출",position = 2)
    private Long loanList;

    @ApiModelProperty(required = true, value = "대출금액",example = "10000",position = 3)
    private Long amount;

    public Loan toEntity(Member member, Account account, LoanList loanList) {
        return Loan.builder()
                .memberId(member)
                .accountNumber(account)
                .loanList(loanList)
                .amount(amount)
                .createDate(new DateConfig().getDate())
                .dueDate(new DateConfig().getDueDate())
                .build();
    }
}
