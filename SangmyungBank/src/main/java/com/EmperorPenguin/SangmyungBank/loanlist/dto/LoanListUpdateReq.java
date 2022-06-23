package com.EmperorPenguin.SangmyungBank.loanlist.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LoanListUpdateReq {

    @ApiModelProperty(required = true)
    private Long id;
    @ApiModelProperty(required = true)
    private String title;
    @ApiModelProperty(required = true)
    private String interestRate;
    @ApiModelProperty(required = true)
    private String interestType;
}
