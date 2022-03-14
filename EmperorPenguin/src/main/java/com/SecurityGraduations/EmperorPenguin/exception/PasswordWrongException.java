package com.SecurityGraduations.EmperorPenguin.exception;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException() {
        super("Password가 옳지 않습니다.");
    }
}
