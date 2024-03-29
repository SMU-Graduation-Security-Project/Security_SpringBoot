package com.EmperorPenguin.SangmyungBank.loanlist.entity;

import com.EmperorPenguin.SangmyungBank.loanlist.dto.LoanListInquiryRes;
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
public class LoanList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true ,nullable = false)
    private String title;

    // 이자율
    @Column(nullable = false)
    private String interestRate;

    // 이자형식
    @Column(nullable = false)
    private String interestType;

    public LoanListInquiryRes toDto(){
        return LoanListInquiryRes.builder()
                .id(id)
                .title(title)
                .interestRate(interestRate)
                .interestType(interestType)
                .build();
    }


}
