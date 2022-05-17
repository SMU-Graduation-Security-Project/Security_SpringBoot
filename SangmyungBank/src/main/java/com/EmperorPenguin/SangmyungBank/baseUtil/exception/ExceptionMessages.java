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
    ERROR_USER_ID_FORMAT("아이디는 영문자로 시작하고 영문자,숫자로 5~12자 이하로 구성되어야합니다."),
    ERROR_USER_PASSWORD_FORMAT("비밀번호는 영문자, 특수문자를 포함한 8~20자로 구성되어야 합니다."),
    ERROR_USER_PASSWORD("사용자의 비밀번호가 일치하지 않습니다."),
    ERROR_USER_NOT_FOUND("사용자를 찾을 수 없습니다.");
    private final String message;
}
