package com.EmperorPenguin.SangmyungBank.event.dto;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
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

    @ApiModelProperty(required = true, value = "이벤트게시물 제목",example = "상명은행이 쏩니다!",position = 0)
    private String title;

    @ApiModelProperty(required = true, value = "이벤트게시물 내용",example = "추첨을 통해 10분께 드립니다.",position = 1)
    private String content;

    @ApiModelProperty(required = true, value = "이벤트 시작일",example = "2022-08-08-00:00:00",position = 2)
    private String startDate;

    @ApiModelProperty(required = true, value = "이벤트 종료일",example = "2022-08-09-00:00:00",position = 3)
    private String endDate;

    public Event toEntity() {
        return Event.builder()
                .title(title)
                .content(content)
                .createDate(new DateConfig().getDate())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
