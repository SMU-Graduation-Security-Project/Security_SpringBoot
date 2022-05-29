package com.EmperorPenguin.SangmyungBank.cardlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CardListRequestRes {

    private String title;
    private String content;

}
