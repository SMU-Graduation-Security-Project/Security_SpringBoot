package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(ExceptionMessages exMessage) {
        super(exMessage.getMessage());
    }
}
