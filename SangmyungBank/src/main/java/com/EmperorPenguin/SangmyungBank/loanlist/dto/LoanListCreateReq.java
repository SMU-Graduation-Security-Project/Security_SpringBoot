package com.EmperorPenguin.SangmyungBank.loanlist.dto;

import com.EmperorPenguin.SangmyungBank.loanlist.entity.LoanList;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LoanListCreateReq {

    @ApiModelProperty(required = true)
    private String title;
    @ApiModelProperty(required = true)
    private String interestRate;
    @ApiModelProperty(required = true)
    private String interestType;

    public LoanList toEntity() {
        return LoanList.builder()
                .title(title)
                .interestRate(interestRate)
                .interestType(interestType)
                .build();
    }
}
