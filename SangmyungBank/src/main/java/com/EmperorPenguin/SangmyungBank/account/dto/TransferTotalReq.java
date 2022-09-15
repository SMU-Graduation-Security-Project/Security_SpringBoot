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
    @ApiModelProperty(required = true, value = "로그인ID",example = "test1234",position = 0)
    private String loginId;

    @ApiModelProperty(required = true, value = "내 계좌번호",example = "102-82-01670",position = 1)
    private Long myAccountNumber;

    @ApiModelProperty(required = true, value = "보내는사람 메세지",example = "메세지",position = 2)
    private String toSenderMessage;

    @ApiModelProperty(required = true, value = "상대방 계좌번호",example = "102-82-01671",position = 3)
    private Long sendAccountNumber;

    @ApiModelProperty(required = true, value = "받는사람 메세지",example = "메세지",position = 4)
    private String toReceiverMessage;

    @ApiModelProperty(required = true, value = "계좌비밀번호",example = "3434",position = 5)
    private String accountPassword;

    @ApiModelProperty(required = true, value = "잔액",example = "10000",position = 6)
    private Long balance;

    @ApiModelProperty(required = true, value = "PK 8자리중 뒤 4자리",example = "1346",position = 7)
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
