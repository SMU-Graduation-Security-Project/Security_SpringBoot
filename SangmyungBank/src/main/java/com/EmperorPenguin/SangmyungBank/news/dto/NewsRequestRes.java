package com.EmperorPenguin.SangmyungBank.news.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class NewsRequestRes {

    private String title;
    private String context;
    private String createdDate;

}
