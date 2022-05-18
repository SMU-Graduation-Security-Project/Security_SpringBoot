package com.EmperorPenguin.SangmyungBank.account.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionReq {
    @ApiModelProperty(required = true)
    private String loginId;

    @ApiModelProperty(required = true)
    private Long myAccountNumber;

    @ApiModelProperty(required = true)
    private Long sendAccountNumber;

    @ApiModelProperty(required = true)
    private String accountPassword;

    @ApiModelProperty(required = true)
    private Long balance;

}
