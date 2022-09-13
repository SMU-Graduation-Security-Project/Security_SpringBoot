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
public class MemberPasswordUpdateReq {

    @ApiModelProperty(required = true, value = "로그인ID",example = "test1234",position = 0)
    private String loginId;
    @ApiModelProperty(required = true, value = "(구)비밀번호",example = "Test1234!",position = 1)
    private String oldPassword;
    @ApiModelProperty(required = true, value = "(신)비밀번호",example = "Test1235",position = 2)
    private String newPassword1;
    @ApiModelProperty(required = true, value = "(신)비밀번호",example = "Test1235",position = 3)
    private String newPassword2;

}
