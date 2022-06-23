package com.EmperorPenguin.SangmyungBank.loanlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LoanListInquiryRes {

    private Long id;
    private String title;
    private String interestRate;
    private String interestType;
}
