package com.EmperorPenguin.SangmyungBank.counsel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CounselUpdateReq {

    @ApiModelProperty(required = true, value = "로그인ID",example = "test1234",position = 0)
    private String loginId;
    @ApiModelProperty(required = true, value = "변경할 게시물ID",example = "1",position = 1)
    private Long id;
    @ApiModelProperty(required = true, value = "변경할 게시물 제목",example = "도와주세요.",position = 2)
    private String title;
    @ApiModelProperty(required = true, value = "변경할 게시물 내용",example = "카드를 삭제하고 싶어요.",position = 3)
    private String content;
}
