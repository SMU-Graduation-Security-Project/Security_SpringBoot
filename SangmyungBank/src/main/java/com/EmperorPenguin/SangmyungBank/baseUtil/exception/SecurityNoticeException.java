package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class SecurityNoticeException extends RuntimeException{

    public SecurityNoticeException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public SecurityNoticeException(String message){
        super(message);
    }
}
