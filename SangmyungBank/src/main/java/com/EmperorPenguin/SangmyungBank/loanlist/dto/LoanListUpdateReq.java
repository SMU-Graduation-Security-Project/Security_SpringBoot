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

    @ApiModelProperty(required = true, value = "대출종류ID",example = "1",position = 0)
    private Long id;
    @ApiModelProperty(required = true, value = "변경할 대출종류",example = "1",position = 1)
    private String title;
    @ApiModelProperty(required = true, value = "변경할 이자율",example = "3.0",position = 2)
    private String interestRate;
    @ApiModelProperty(required = true, value = "변경할 대출타입",example = "변동금리",position = 3)
    private String interestType;
}
