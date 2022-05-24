package com.EmperorPenguin.SangmyungBank.transaction.entity;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.transaction.dto.TransactionInquiryRes;
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

    @Column
    private Long sendAccount;

    @Column
    private Long receiveAccount;

    @Column(nullable = false)
    private Long balance;

    public TransactionInquiryRes toDto() {
        if (balance > 0) {
            return TransactionInquiryRes.builder()
                    .senderAccount(receiveAccount)
                    .receiverAccount(sendAccount)
                    .sendMoney(balance)
                    .build();
        } else {
            return TransactionInquiryRes.builder()
                    .senderAccount(sendAccount)
                    .receiverAccount(receiveAccount)
                    .sendMoney(balance)
                    .build();
        }
    }
}
