package com.EmperorPenguin.SangmyungBank.baseUtil.dto;

import lombok.Data;

@Data
public class BaseResult {
    boolean success;
    int code;
    String message;
}
