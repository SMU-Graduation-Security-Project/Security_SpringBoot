package com.EmperorPenguin.SangmyungBank.winner.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WinnerUpdateReq {
    @ApiModelProperty(required = true, value = "당첨자게시물ID",example = "1",position = 0)
    private Long id;

    @ApiModelProperty(required = true, value = "변경할 당첨자 내용",example = "날짜 + -HH:MM:SS",position = 1)
    private String content;
}
