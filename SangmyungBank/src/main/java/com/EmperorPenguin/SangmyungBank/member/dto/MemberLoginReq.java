package com.EmperorPenguin.SangmyungBank.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberLoginReq {

    @ApiModelProperty(required = true)
    private String loginId;
    @ApiModelProperty(required = true)
    private String password;
}
