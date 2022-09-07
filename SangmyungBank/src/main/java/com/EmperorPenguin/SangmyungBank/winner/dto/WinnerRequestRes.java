package com.EmperorPenguin.SangmyungBank.winner.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WinnerRequestRes {

    private Long id;
    private Long eventId;
    private String title;
    private String content;
    private String createDate;

}
