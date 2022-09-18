package com.EmperorPenguin.SangmyungBank.account.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountInquiryRes {

    private Long accountNumber;
    private String accountType;
    private Long balance;
    private String name;
}
