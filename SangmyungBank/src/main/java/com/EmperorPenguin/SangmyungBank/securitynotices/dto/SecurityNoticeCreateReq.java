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

    @ApiModelProperty(required = true, value = "보안공지 제목",example = "보이스 피싱 주의 안내",position = 0)
    private String title;
    @ApiModelProperty(required = true, value = "보안공지 내용",example = "보이스 피싱을 조심하세요.",position = 1)
    private String content;

    public SecurityNotices toEntity() {
        return SecurityNotices.builder()
                .title(title)
                .content(content)
                .createdDate(new DateConfig().getDate())
                .build();
    }
}
