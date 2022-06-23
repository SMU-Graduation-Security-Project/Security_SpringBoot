package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class WinnerException extends RuntimeException{
    public WinnerException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public WinnerException(String message){
        super(message);
    }
}
