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
public class MemberFindPasswordReq {

    @ApiModelProperty(required = true, value = "로그인ID",example = "test1234",position = 0)
    private String loginId;
    @ApiModelProperty(required = true, value = "질문",example = "존경하는 교수님은?",position = 1)
    private String question;
    @ApiModelProperty(required = true, value = "답",example = "박종환교수님",position = 2)
    private String ansWord;

}
