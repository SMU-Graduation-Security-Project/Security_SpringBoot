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
        // -일때는 Sender에서 Receiver에게 돈을 준 것임.
        if (this.balance < 0)
            return TransactionInquiryRes.builder()
                    .senderAccount(sendAccount)
                    .toSenderMessage(toSenderMessage)
                    .receiverAccount(receiveAccount)
                    .toReceiverMessage(toReceiverMessage)
                    .sendMoney(balance)
                    .transactionDate(transactionDate)
                    .build();
            // 양수일때  Sender는 Receiver에게 돈을 받음
        else
            return TransactionInquiryRes.builder()
                    .senderAccount(receiveAccount)
                    .toSenderMessage(toReceiverMessage)
                    .receiverAccount(sendAccount)
                    .toReceiverMessage(toSenderMessage)
                    .sendMoney(balance)
                    .transactionDate(transactionDate)
                    .build();
    }
}
