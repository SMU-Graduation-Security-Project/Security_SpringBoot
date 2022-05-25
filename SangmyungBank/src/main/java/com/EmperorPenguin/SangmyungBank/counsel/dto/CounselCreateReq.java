package com.EmperorPenguin.SangmyungBank.counsel.dto;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.counsel.entity.Counsel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CounselCreateReq {
    @ApiModelProperty(required = true)
    private String title;
    @ApiModelProperty(required = true)
    private String content;

    public Counsel toEntity() {
        return Counsel.builder()
                .title(title)
                .content(content)
                .createDate(new DateConfig().getDateTime())
                .build();
    }
}
