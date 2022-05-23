package com.EmperorPenguin.SangmyungBank.event.dto;

import com.EmperorPenguin.SangmyungBank.event.entity.Event;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EventCreateReq {

    @ApiModelProperty(required = true)
    private String title;

    @ApiModelProperty(required = true)
    private String content;

    @ApiModelProperty(required = true)
    private String startDate;

    @ApiModelProperty(required = true)
    private String endDate;

    public Event toEntity() {
        return Event.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}