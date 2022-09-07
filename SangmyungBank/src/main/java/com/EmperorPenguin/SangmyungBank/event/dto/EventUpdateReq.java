package com.EmperorPenguin.SangmyungBank.event.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EventUpdateReq {

    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(required = true)
    private String title;

    @ApiModelProperty(required = true)
    private String content;

    @ApiModelProperty(required = true)
    private String startDate;

    @ApiModelProperty(required = true)
    private String endDate;

}
