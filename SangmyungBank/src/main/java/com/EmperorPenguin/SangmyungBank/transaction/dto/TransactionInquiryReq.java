package com.EmperorPenguin.SangmyungBank.transaction.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionInquiryReq {

    @ApiModelProperty(required = true, notes = "날짜 + -HH:MM:SS")
    private Long accountNumber;
    @ApiModelProperty(required = true, notes = "날짜 + -HH:MM:SS")
    private String startDate;
    @ApiModelProperty(required = true, notes = "날짜 + -HH:MM:SS")
    private String endDate;

}
