package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(ExceptionMessages message) {
        super(message.getMessage());
    }
}