package com.EmperorPenguin.SangmyungBank.user.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.user.dto.UserLoginReq;
import com.EmperorPenguin.SangmyungBank.user.dto.UserRegisterReq;
import com.EmperorPenguin.SangmyungBank.user.service.UserService;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@Api(tags = "회원가입,로그인")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UserController {
    private final UserService userService;
    private final ResponseService responseService;

    @PostMapping(path = "/register")
    @ApiOperation(value="회원가입")
    public BaseResult addUser(@ApiParam @RequestBody UserRegisterReq userRegisterReq) {
        // 이전 boolean 통해 오류를 검출하는 방식으로 작동
        // refactoring 이후 Exception 통한 예외처리로 로직 변경
        // 아이디 중복, 이메일 중복, 전화번호 중복, 비밀번호 규칙 검증, 올바르게 비밀번호를 2번 입력했는지 확인
        try {
            userService.register(userRegisterReq);
            return responseService.successResult();
        } catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PostMapping(path = "/login")
    @ApiOperation(value="로그인")
    public BaseResult authUser(@ApiParam @RequestBody UserLoginReq userLoginReq){
        // 이전 boolean 통해 오류를 검출하는 방식으로 작동
        // refactoring 이후 Exception 통한 예외처리로 로직 변경
        // 없는 아이디, 잘못된 비밀번호에서 오류 발생.
        try{
            return responseService.singleResult(userService.login(userLoginReq).toDto());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}


