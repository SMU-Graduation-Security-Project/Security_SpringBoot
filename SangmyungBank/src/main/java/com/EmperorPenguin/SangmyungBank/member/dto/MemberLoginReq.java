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

    @ApiModelProperty(required = true, value = "로그인ID",example = "test1234",position = 0)
    private String loginId;
    @ApiModelProperty(required = true, value = "비밀번호",example = "Test1234!",position = 1)
    private String password;
}
