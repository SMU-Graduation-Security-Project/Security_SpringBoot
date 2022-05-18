package com.EmperorPenguin.SangmyungBank.account.dto;

import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.user.entity.User;
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

    public Account toEntity(User User, String encodedPassword, Long balance){
        return Account.builder()
                .userId(User)
                .accountPassword(encodedPassword)
                .balance(balance)
                .CreateDate(new DateConfig().getDateTime())
                .build();
    }
}
