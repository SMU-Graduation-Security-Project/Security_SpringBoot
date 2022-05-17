package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessages {
    ERROR_UNDEFINED("정의되지 않은 에러"),
    ERROR_USER_ID_DUPLICATE("이미 사용중인 아이디 입니다."),
    ERROR_USER_EMAIL_DUPLICATE("이미 사용중인 이메일입니다."),
    ERROR_USER_PHONENUMBER_DUPLICATE("이미 사용중인 전화번호입니다."),
    ERROR_USER_PASSWORD_FORMAT("비밀번호 형식을 맞춰주세요"),
    ERROR_USER_PASSWORD("사용자의 비밀번호가 일치하지 않습니다.");

    private final String message;
}
