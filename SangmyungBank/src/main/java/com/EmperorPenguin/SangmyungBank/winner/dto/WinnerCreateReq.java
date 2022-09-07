package com.EmperorPenguin.SangmyungBank.winner.dto;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.event.entity.Event;
import com.EmperorPenguin.SangmyungBank.winner.entity.Winner;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WinnerCreateReq {

    @ApiModelProperty(required = true)
    private Long eventId;

    @ApiModelProperty(required = true)
    private String content;

    public Winner toEntity(Event event) {
        return Winner.builder()
                .eventId(event)
                .content(content)
                .createDate(new DateConfig().getDate())
                .build();
    }
}
