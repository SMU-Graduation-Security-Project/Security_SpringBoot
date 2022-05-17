package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class UserException extends RuntimeException {

    public UserException() {
        super();
    }

    // 기본적인 예외 메시지를 통한 오류 발생
    public UserException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    // 정의되지 않은 예외 메시지를 직접 작성
    public UserException(String message) {
        super(message);
    }

}
