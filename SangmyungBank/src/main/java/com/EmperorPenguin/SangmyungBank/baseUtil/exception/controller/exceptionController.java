package com.EmperorPenguin.SangmyungBank.baseUtil.exception.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.AccessDeniedException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.BusinessException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/exception")
public class exceptionController {

    @GetMapping("/entrypoint")
    public void entrypointException() {
        throw new BusinessException(ExceptionMessages.ERROR_JWT_FORMAT);
    }

    @GetMapping("/accessDenied")
    public void accessDeniedException(HttpServletResponse response) {
        throw new AccessDeniedException(ExceptionMessages.ERROR_JWT_ACCESS_DENIED);
    }
}