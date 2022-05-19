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

    @ApiModelProperty(required = true)
    private Long id;
    @ApiModelProperty(required = true)
    private String title;
    @ApiModelProperty(required = true)
    private String context;
}
