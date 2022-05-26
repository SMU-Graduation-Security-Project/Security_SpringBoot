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

    @ApiModelProperty(required = true, value = "글을 수정할 사용자의 아이디")
    private String loginId;
    @ApiModelProperty(required = true, value = "수정할 글의 번호")
    private Long id;
    @ApiModelProperty(required = true, value = "수정할 글의 제목")
    private String title;
    @ApiModelProperty(required = true, value = "수정할 글의 세부 내용")
    private String content;
}
