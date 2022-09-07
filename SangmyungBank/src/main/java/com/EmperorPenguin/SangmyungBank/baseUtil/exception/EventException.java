package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class EventException extends RuntimeException{

    public EventException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public EventException(String message){
        super(message);
    }
}
