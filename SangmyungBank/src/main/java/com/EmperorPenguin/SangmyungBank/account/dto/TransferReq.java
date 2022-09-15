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
public class TransferReq {
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
}
