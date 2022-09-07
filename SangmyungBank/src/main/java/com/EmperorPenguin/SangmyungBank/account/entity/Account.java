package com.EmperorPenguin.SangmyungBank.account.entity;

import com.EmperorPenguin.SangmyungBank.account.dto.AccountInquiryRes;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name="memberId")
    private Member memberId;

    @Id
    private Long accountNumber;

    @Column(columnDefinition = "text",nullable = false)
    private String accountPassword;

    @Column(nullable = false)
    private Long balance;

    @Column
    private String createDate;

    @Column
    private String accountType;

    public AccountInquiryRes toDto() {
        return AccountInquiryRes.builder()
                .accountNumber(accountNumber)
                .accountType(accountType)
                .balance(balance)
                .build();
    }
}