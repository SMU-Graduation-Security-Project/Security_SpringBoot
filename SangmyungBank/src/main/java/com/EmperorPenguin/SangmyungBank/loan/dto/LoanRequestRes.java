package com.EmperorPenguin.SangmyungBank.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LoanRequestRes {
    private Long id;
    private Long accountNumber;
    private String loanList;
    private String interestRate;
    private String interestType;
    private Long amount;
    private Long balance;
    private String createDate;
    private String dueDate;
}
