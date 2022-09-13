package com.EmperorPenguin.SangmyungBank.news.dto;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.news.entity.News;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class NewsCreateReq {

    @ApiModelProperty(required = true, value = "제목",example = "2022년 상명은행 개편 소식알림.",position = 0)
    private String title;
    @ApiModelProperty(required = true, value = "내용",example = "상명은행 개편 소식을 알려드립니다.",position = 1)
    private String content;

    public News toEntity() {
        return News.builder()
                .title(title)
                .content(content)
                .createdDate(new DateConfig().getDate())
                .build();
    }
}
