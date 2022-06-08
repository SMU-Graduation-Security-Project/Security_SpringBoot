package com.EmperorPenguin.SangmyungBank.member.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberPasswordUpdateReq;
import com.EmperorPenguin.SangmyungBank.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "1. 사용자")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {
    private final MemberService memberService;
    private final ResponseService responseService;

    @GetMapping(path = "/")
    @ApiOperation(value = "사용자 정보 가져오기", notes = "loginId에 해당하는 사용자의 정보를 가져옵니다.")
    public BaseResult getUserData(@ApiParam @RequestParam String loginId){
        try{
            return responseService.singleResult(memberService.getMemberData(loginId));
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PostMapping(path = "/updatePassword")
    @ApiOperation(value = "사용자 비밀번호 변경",notes = "사용자이전 비밀번호에서 새로운 비밀번호로 변경합니다.")
    public BaseResult updateUserPassword(@ApiParam @RequestBody MemberPasswordUpdateReq memberPasswordUpdateReq){
        try {
            memberService.updateNewPassword(memberPasswordUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }


}
