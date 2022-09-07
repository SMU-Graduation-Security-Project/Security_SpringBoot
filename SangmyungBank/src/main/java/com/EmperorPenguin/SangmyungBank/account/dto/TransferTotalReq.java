package com.EmperorPenguin.SangmyungBank.account.dto;

import com.EmperorPenguin.SangmyungBank.otp.dto.OtpValidReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TransferTotalReq {
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

    @ApiModelProperty(required = true)
    private int pkOTP4;

    @ApiModelProperty(required = true, notes = "첫번쨰 봅은 숫자는 무조껀 앞의 2자리")
    private int selectNum1;

    @ApiModelProperty(required = true, notes = "두번째 뽑은 숫자는 무조껀 뒤의 2자리")
    private int selectNum2;

    @ApiModelProperty(required = true)
    private int ansNum1;

    @ApiModelProperty(required = true)
    private int ansNum2;

    public TransferReq toTransfer() {
        return TransferReq.builder()
                .loginId(loginId)
                .myAccountNumber(myAccountNumber)
                .toSenderMessage(toSenderMessage)
                .sendAccountNumber(sendAccountNumber)
                .toReceiverMessage(toReceiverMessage)
                .accountPassword(accountPassword)
                .balance(balance)
                .build();
    }

    public OtpValidReq toOtp(){
        return OtpValidReq.builder()
                .loginId(loginId)
                .pkOTP4(pkOTP4)
                .selectNum1(selectNum1)
                .selectNum2(selectNum2)
                .ansNum1(ansNum1)
                .ansNum2(ansNum2)
                .build();
    }
}
