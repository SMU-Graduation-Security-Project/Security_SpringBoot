package com.EmperorPenguin.SangmyungBank.securitynotices.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SecurityNoticeUpdateReq {

    @ApiModelProperty(required = true, value = "보안공지ID",example = "1",position = 0)
    private Long id;
    @ApiModelProperty(required = true, value = "변경할 보안공지 제목",example = "보이스 피싱 주의 안내",position = 0)
    private String title;
    @ApiModelProperty(required = true, value = "변경할 보안공지 내용",example = "보이스 피싱을 조심하세요.",position = 0)
    private String content;
}
