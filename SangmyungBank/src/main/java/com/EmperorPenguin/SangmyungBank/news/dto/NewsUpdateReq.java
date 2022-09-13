package com.EmperorPenguin.SangmyungBank.news.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class NewsUpdateReq {

    @ApiModelProperty(required = true, value = "변경할 게시물ID",example = "1",position = 0)
    private Long id;
    @ApiModelProperty(required = true, value = "업데이트 할 제목",example = "상명은행에서 알려드립니다.",position = 1)
    private String title;
    @ApiModelProperty(required = true, value = "업데이트 할 내용",example = "상명은행 약관이 개정되었습니다.",position = 2)
    private String content;
}
