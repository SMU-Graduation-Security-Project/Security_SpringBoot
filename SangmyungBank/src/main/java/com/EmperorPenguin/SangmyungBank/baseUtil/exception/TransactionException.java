package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class TransactionException extends RuntimeException{

    public TransactionException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public TransactionException(String message){
        super(message);
    }
}
