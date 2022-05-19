package com.EmperorPenguin.SangmyungBank.securitynotices.dto;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.securitynotices.entity.SecurityNotices;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SecurityNoticeCreateReq {

    @ApiModelProperty(required = true)
    private String title;
    @ApiModelProperty(required = true)
    private String content;

    public SecurityNotices toEntity() {
        return SecurityNotices.builder()
                .title(title)
                .content(content)
                .createdDate(new DateConfig().getDateTime())
                .build();
    }
}
