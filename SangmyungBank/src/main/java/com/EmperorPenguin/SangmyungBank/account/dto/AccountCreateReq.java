package com.EmperorPenguin.SangmyungBank.account.dto;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountCreateReq {

    @ApiModelProperty(required = true, value = "로그인ID",example = "test1234",position = 0)
    private String loginId;


    private String accountPassword;

    @ApiModelProperty(required = true, value = "계좌타입",example = "",position = 2)
    private String accountType;

    public Account toEntity(Member Member, Long accountNumber, String encodedPassword, Long balance){
        return Account.builder()
                .memberId(Member)
                .accountNumber(accountNumber)
                .accountPassword(encodedPassword)
                .balance(balance)
                .createDate(new DateConfig().getDateTime())
                .accountType(accountType)
                .build();
    }
}
