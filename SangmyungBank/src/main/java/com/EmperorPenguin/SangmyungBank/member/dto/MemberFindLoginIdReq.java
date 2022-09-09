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
public class MemberFindLoginIdReq {

    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = true)
    private String phoneNumber;
    @ApiModelProperty(required = true)
    private String question;
    @ApiModelProperty(required = true)
    private String ansWord;

}
