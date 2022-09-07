package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class AccountException extends RuntimeException{

    public AccountException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public AccountException(String message){
        super(message);
    }
}
