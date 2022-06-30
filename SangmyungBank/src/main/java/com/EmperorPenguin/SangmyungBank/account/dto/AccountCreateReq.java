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

    @ApiModelProperty(required = true)
    private String loginId;

    @ApiModelProperty(required = true)
    private String accountPassword;

    public Account toEntity(Member Member, Long accountNumber, String encodedPassword, Long balance){
        return Account.builder()
                .memberId(Member)
                .accountNumber(accountNumber)
                .accountPassword(encodedPassword)
                .balance(balance)
                .CreateDate(new DateConfig().getDateTime())
                .bankName("상명은행")
                .build();
    }
}
