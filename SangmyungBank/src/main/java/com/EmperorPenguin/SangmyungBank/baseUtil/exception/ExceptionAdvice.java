package com.EmperorPenguin.SangmyungBank.baseUtil.exception;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResult businessExceptionHandler(HttpServletRequest request, Exception e) {
        log.error("\n==========================================================================================\n"
                + e.getMessage()
                + "\n==========================================================================================");
        return responseService.failResult(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public BaseResult accessDeniedExceptionHandler(HttpServletRequest request, Exception e) {
        log.error("\n==========================================================================================\n"
                + e.getMessage()
                + "\n==========================================================================================");
        return responseService.failResult(e.getMessage());
    }

}
