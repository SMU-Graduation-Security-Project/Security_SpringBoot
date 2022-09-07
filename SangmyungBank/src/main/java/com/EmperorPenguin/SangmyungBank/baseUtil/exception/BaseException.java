package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class BaseException extends RuntimeException {

    public BaseException(ExceptionMessages exMessage) {
        super(exMessage.getMessage());
    }

    public BaseException(String message){
        super(message);
    }

}
