package com.EmperorPenguin.SangmyungBank.baseUtil.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BaseResponse {
    SUCCESS("성공"),
    FAIL("실패");

    private final String message;
}
