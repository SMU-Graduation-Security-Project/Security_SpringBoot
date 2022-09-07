package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class OtpException extends RuntimeException{

    public OtpException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public OtpException(String message){
        super(message);
    }
}