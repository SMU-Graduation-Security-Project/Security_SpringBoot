package com.EmperorPenguin.SangmyungBank.member.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberLoginReq;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberRegisterReq;
import com.EmperorPenguin.SangmyungBank.member.service.MemberService;
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
public class MemberController {
    private final MemberService memberService;
    private final ResponseService responseService;

    @PostMapping(path = "/register")
    @ApiOperation(value="회원가입", notes = "사용자 정보를 받아 사용자를 저장합니다.")
    public BaseResult addUser(@ApiParam @RequestBody MemberRegisterReq memberRegisterReq) {
        // 이전 boolean 통해 오류를 검출하는 방식으로 작동
        // refactoring 이후 Exception 통한 예외처리로 로직 변경
        // 아이디 중복, 이메일 중복, 전화번호 중복, 비밀번호 규칙 검증, 올바르게 비밀번호를 2번 입력했는지 확인
        try {
            memberService.register(memberRegisterReq);
            return responseService.successResult();
        } catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PostMapping(path = "/login")
    @ApiOperation(value="로그인", notes = "사용자의 아이디와 비밀번호를 받아 로그인을 합니다.")
    public BaseResult authUser(@ApiParam @RequestBody MemberLoginReq memberLoginReq){
        // 이전 boolean 통해 오류를 검출하는 방식으로 작동
        // refactoring 이후 Exception 통한 예외처리로 로직 변경
        // 없는 아이디, 잘못된 비밀번호에서 오류 발생.
        try{
            return responseService.singleResult(memberService.login(memberLoginReq).toLoginDto());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }


}


