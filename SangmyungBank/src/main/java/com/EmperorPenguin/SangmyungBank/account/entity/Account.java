package com.EmperorPenguin.SangmyungBank.account.entity;

import com.EmperorPenguin.SangmyungBank.account.dto.AccountInquiryRes;
import com.EmperorPenguin.SangmyungBank.user.entity.User;
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

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Column(columnDefinition = "text",nullable = false)
    private String accountPassword;

    @Column(nullable = false)
    private Long balance;

    @Column
    private String CreateDate;

    public AccountInquiryRes toDto() {
        return AccountInquiryRes.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .build();
    }
}