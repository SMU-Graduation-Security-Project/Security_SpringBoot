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

    @ApiModelProperty(required = true, value = "이름",example = "홍길동",position = 0)
    private String name;
    @ApiModelProperty(required = true, value = "이메일",example = "test1234@gmail.com",position = 1)
    private String email;

}
