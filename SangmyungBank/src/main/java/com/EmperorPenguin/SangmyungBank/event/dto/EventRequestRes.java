package com.EmperorPenguin.SangmyungBank.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class EventRequestRes {

    private String title;
    private String content;
    private String startDate;
    private String endDate;
}
