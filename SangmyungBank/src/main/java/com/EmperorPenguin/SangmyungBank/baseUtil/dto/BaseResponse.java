package com.EmperorPenguin.SangmyungBank.baseUtil.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BaseResponse {

    SUCCESS(1,"성공"),
    Fail(0,"실패");

    private final int code;
    private final String message;
}
