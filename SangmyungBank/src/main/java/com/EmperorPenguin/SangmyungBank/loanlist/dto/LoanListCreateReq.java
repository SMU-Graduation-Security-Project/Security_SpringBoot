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

    @ApiModelProperty(required = true, value = "대출종류",example = "학자금대출",position = 0)
    private String title;
    @ApiModelProperty(required = true, value = "이자율",example = "3.0",position = 1)
    private String interestRate;
    @ApiModelProperty(required = true, value = "대출타입",example = "고정금리",position = 2)
    private String interestType;

    public LoanList toEntity() {
        return LoanList.builder()
                .title(title)
                .interestRate(interestRate)
                .interestType(interestType)
                .build();
    }
}
