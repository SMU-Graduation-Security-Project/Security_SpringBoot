package com.EmperorPenguin.SangmyungBank.home.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.service.JwtService;
import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.member.dto.*;
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

import javax.servlet.http.HttpServletRequest;

@Api(tags = "00. 홈")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class HomeController {
    private final MemberService memberService;
    private final ResponseService responseService;
    private final JwtService jwtService;

    @PostMapping(path = "/login")
    @ApiOperation(value="1. 로그인", notes = "아이디,패스워드를 사용하여 로그인")
    public void authUser(
            @ApiParam(value ="로그인 객체", required = true)
            @ModelAttribute MemberLoginReq memberLoginReq
    ){
        // 이전 boolean 통해 오류를 검출하는 방식으로 작동
        // refactoring 이후 Exception 통한 예외처리로 로직 변경
        // 없는 아이디, 잘못된 비밀번호에서 오류 발생.
//        try{
//            return responseService.singleResult(memberService.login(memberLoginReq).toLoginDto());
//        }catch (Exception e){
//            return responseService.failResult(
//                    e.getMessage()
//            );
//        }
    }

    @PostMapping(path = "/logout")
    @ApiOperation(value = "로그아웃", notes = "AccessToken & RefreshToken 헤더에 담아서 로그아웃 요청")
    public BaseResult logoutUser(HttpServletRequest request){
        try {
            jwtService.logout(request);
            return responseService.successResult();}
        catch(Exception e){
            return  responseService.failResult(e.getMessage());
        }
    }

    @PostMapping(path = "/register")
    @ApiOperation(value="3. 회원가입", notes = "사용자 정보를 입력받아 사용자를 저장")
    public BaseResult saveUser(
            @ApiParam(value = "회원 객체", required = true)
            @ModelAttribute MemberRegisterReq memberRegisterReq) {
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

    @PostMapping(path = "/find_password")
    @ApiOperation(value = "4. 비밀번호 찾기", notes = "회원가입시 등록한 질문과 정답을 사용해 임시비밀번호를 제공")
    public BaseResult findPassword(
            @ApiParam(value = "비밀번호를 찾기 객체", required = true)
            @ModelAttribute MemberFindPasswordReq memberFindPasswordReq){
        try {
            return responseService.singleResult(
                    memberService.setTemplatePassword(memberFindPasswordReq));
        }catch (Exception e){
            return  responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PostMapping(path = "/update_temp_password")
    @ApiOperation(value = "5. 임시 비밀번호 변경",notes = "임시 비밀번호를 제공받은 유저의 새로운 비밀번호로 비밀번호를 변경합니다.")
    public BaseResult updateTemplateUserPassword(
            @ApiParam(value = "비밀번호 변경 객체", required = true)
            @ModelAttribute MemberPasswordUpdateReq memberPasswordUpdateReq){
        try {
            memberService.updateTemplatePassword(memberPasswordUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PostMapping(path = "/find_login_id")
    @ApiOperation(value = "6. 아이디 찾기 ",notes = "아이디를 찾습니다.")
    public BaseResult findLoginId(
            @ApiParam(value = "아이디를 찾기 객체", required = true)
            @ModelAttribute MemberFindLoginIdReq memberFindLoginIdReq){
        try {
            return responseService.singleResult(
                    memberService.findLoginId(memberFindLoginIdReq)
            );
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}


