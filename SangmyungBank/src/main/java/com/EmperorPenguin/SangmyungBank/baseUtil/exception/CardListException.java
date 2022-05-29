package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class CardListException extends RuntimeException{

    public CardListException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public CardListException(String message){
        super(message);
    }
}
