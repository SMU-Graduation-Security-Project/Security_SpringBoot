package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class CardException extends RuntimeException{

    public CardException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public CardException(String message){
        super(message);
    }
}
