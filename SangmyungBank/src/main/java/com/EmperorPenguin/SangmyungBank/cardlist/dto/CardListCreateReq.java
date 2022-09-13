package com.EmperorPenguin.SangmyungBank.cardlist.dto;

import com.EmperorPenguin.SangmyungBank.cardlist.entity.CardList;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CardListCreateReq {

    @ApiModelProperty(required = true, value = "카드이름",example = "상명카드",position = 0)
    private String title;
    @ApiModelProperty(required = true, value = "카드내용",example = "이자율은 몇 퍼센트이고 금리는 몇 퍼센트입니다.",position = 1)
    private String content;

    public CardList toEntity() {
        return CardList.builder()
                .title(title)
                .content(content)
                .build();
    }
}
