package com.EmperorPenguin.SangmyungBank.transaction.entity;

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

    @Column
    private String toSenderMessage;

    @Column
    private String toReceiverMessage;

    @Column
    private String transactionDate;

    public TransactionInquiryRes toDto() {
        return TransactionInquiryRes.builder()
                .senderAccount(sendAccount)
                .toSenderMessage(toSenderMessage)
                .receiverAccount(receiveAccount)
                .toReceiverMessage(toReceiverMessage)
                .sendMoney(balance)
                .transactionDate(transactionDate)
                .build();
    }
}
