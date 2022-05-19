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

    @ApiModelProperty(required = true)
    private String title;
    @ApiModelProperty(required = true)
    private String content;

    public News toEntity() {
        return News.builder()
                .title(title)
                .content(content)
                .createdDate(new DateConfig().getDateTime())
                .build();
    }
}
