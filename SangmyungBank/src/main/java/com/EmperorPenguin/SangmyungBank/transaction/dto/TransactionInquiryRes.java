package com.EmperorPenguin.SangmyungBank.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionInquiryRes {

    private Long senderAccount;
    private Long receiverAccount;
    private Long sendMoney;
}
