package com.SecurityGraduations.EmperorPenguin.exception;

public class IdExistedException extends RuntimeException {
    public IdExistedException(String id) {
        super(id+"는 이미 존재합니다.");
    }
}
