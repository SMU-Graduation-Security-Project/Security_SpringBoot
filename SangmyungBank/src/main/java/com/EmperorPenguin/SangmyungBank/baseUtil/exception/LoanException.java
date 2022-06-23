package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class LoanException extends RuntimeException{

    public LoanException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public LoanException(String message){
        super(message);
    }
}
