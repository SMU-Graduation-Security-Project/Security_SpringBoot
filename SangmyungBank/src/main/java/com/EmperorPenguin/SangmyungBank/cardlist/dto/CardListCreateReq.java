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

    @ApiModelProperty(required = true)
    private String title;
    @ApiModelProperty(required = true)
    private String content;

    public CardList toEntity() {
        return CardList.builder()
                .title(title)
                .content(content)
                .build();
    }
}
