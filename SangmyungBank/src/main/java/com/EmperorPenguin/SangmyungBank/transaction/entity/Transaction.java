package com.EmperorPenguin.SangmyungBank.transaction.entity;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @OneToOne
    @JoinColumn(name = "Account")
    private Account send;


    @OneToOne
    @JoinColumn(name = "Account")
    private Account to;

    @Column(nullable = false)
    private Long balance;
}
