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
    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(required = true)
    private String content;
}
