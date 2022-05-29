package com.EmperorPenguin.SangmyungBank.counsel.dto;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.counsel.entity.Counsel;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
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
    private String loginId;
    @ApiModelProperty(required = true)
    private String title;
    @ApiModelProperty(required = true)
    private String content;

    public Counsel toEntity(Member member) {
        return Counsel.builder()
                .title(title)
                .content(content)
                .createDate(new DateConfig().getDateTime())
                .modifyDate(new DateConfig().getDateTime())
                .memberId(member)
                .build();
    }
}
