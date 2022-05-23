package com.EmperorPenguin.SangmyungBank.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserPasswordUpdateReq {

    @ApiModelProperty(required = true)
    private String loginId;
    @ApiModelProperty(required = true)
    private String oldPassword;
    @ApiModelProperty(required = true)
    private String newPassword1;
    @ApiModelProperty(required = true)
    private String newPassword2;

}
