package com.EmperorPenguin.SangmyungBank.loan.entity;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.card.dto.CardRequestRes;
import com.EmperorPenguin.SangmyungBank.loan.dto.LoanRequestRes;
import com.EmperorPenguin.SangmyungBank.loanlist.entity.LoanList;
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
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="accountNumber")
    private Account accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="loanList")
    private LoanList loanList;

    private Long amount;

    public LoanRequestRes toDto(){
        return LoanRequestRes.builder()
                .id(id)
                .accountNumber(accountNumber.getAccountNumber())
                .loanList(loanList.getId())
                .amount(amount)
                .balance(accountNumber.getBalance())
                .build();
    }
}
