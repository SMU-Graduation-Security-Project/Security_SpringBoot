package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class CounselException extends RuntimeException{

    public CounselException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public CounselException(String message){
        super(message);
    }
}
