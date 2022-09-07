package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class LoanListException extends RuntimeException{

    public LoanListException(ExceptionMessages exceptionMessages) {
        super(exceptionMessages.getMessage());
    }

    public LoanListException(String message){
        super(message);
    }
}
