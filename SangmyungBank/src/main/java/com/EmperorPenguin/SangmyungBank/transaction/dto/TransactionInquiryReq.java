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

    @ApiModelProperty(required = true, value = "계좌번호",example = "1111",position = 0)
    private Long accountNumber;
    @ApiModelProperty(required = true, value = "계좌 조회 시작일",example = "날짜 + -HH:MM:SS",position = 1)
    private String startDate;
    @ApiModelProperty(required = true, value = "계좌 조회 종료일",example = "날짜 + -HH:MM:SS",position = 2)
    private String endDate;
}
