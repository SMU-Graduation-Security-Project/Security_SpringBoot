package com.EmperorPenguin.SangmyungBank.account.dto;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.transaction.entity.Transaction;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TransferReq {
    @ApiModelProperty(required = true)
    private String loginId;

    @ApiModelProperty(required = true)
    private Long myAccountNumber;

    @ApiModelProperty(required = true)
    private String toSenderMessage;

    @ApiModelProperty(required = true)
    private Long sendAccountNumber;

    @ApiModelProperty(required = true)
    private String toReceiverMessage;

    @ApiModelProperty(required = true)
    private String accountPassword;

    @ApiModelProperty(required = true)
    private Long balance;

}
