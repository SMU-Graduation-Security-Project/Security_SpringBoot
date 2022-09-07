package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class NewsException extends RuntimeException{

    public NewsException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public NewsException(String message){
        super(message);
    }
}
