package com.EmperorPenguin.SangmyungBank.counsel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CounselRequestRes {
    private String title;
    private String content;
    private String writeDate;
}
