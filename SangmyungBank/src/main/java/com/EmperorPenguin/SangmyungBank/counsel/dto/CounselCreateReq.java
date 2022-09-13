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

    @ApiModelProperty(required = true, value = "로그인ID",example = "test1234",position = 0)
    private String loginId;
    @ApiModelProperty(required = true, value = "상담게시물 제목",example = "도와주세요.",position = 1)
    private String title;
    @ApiModelProperty(required = true, value = "상담게시물 내용",example = "계좌이체가 안되요.",position = 2)
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
