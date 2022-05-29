package com.EmperorPenguin.SangmyungBank.memberUtil.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.memberUtil.dto.MemberFindPasswordReq;
import com.EmperorPenguin.SangmyungBank.memberUtil.dto.MemberPasswordUpdateReq;
import com.EmperorPenguin.SangmyungBank.memberUtil.service.MemberUtilService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "비밀번호 찾기, 비밀번호 수정, 사용자 정보 가져오기")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberUtilController {
    private final MemberUtilService memberUtilService;
    private final ResponseService responseService;

    @PostMapping(path = "/find_Password")
    @ApiOperation(value = "비밀번호 찾기")
    public BaseResult findPassword(@ApiParam @RequestBody MemberFindPasswordReq memberFindPasswordReq){
        try {
            return responseService.singleResult(
                    memberUtilService.setTemplatePassword(memberFindPasswordReq));
        }catch (Exception e){
            return  responseService.failResult(
                    e.getMessage()
            );
        }
    }


    @PostMapping(path = "/updatePassword")
    @ApiOperation(value = "임시 비밀번호를 가진 사용자의 비밀번호 변경")
    public BaseResult updateUserPassword(@ApiParam @RequestBody MemberPasswordUpdateReq memberPasswordUpdateReq){
        try {
            memberUtilService.updateNewPassword(memberPasswordUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping(path = "/")
    @ApiOperation(value = "사용자 정보 가져오기")
    public BaseResult getUserData(@ApiParam @RequestParam String loginId){
        try{
            return responseService.singleResult(memberUtilService.getMemberData(loginId));
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
