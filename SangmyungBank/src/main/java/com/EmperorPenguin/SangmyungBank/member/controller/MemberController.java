package com.EmperorPenguin.SangmyungBank.member.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberInquiryReq;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberPasswordUpdateReq;
import com.EmperorPenguin.SangmyungBank.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "01. 사용자")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class MemberController {
    private final MemberService memberService;
    private final ResponseService responseService;

    @GetMapping(path = "/")
    @ApiOperation(value = "1. 사용자 정보 가져오기", notes = "loginId에 해당하는 사용자의 정보를 가져옵니다.")
    public BaseResult getUserData(
            @ApiParam(value = "아이디로 사용자 조회", required = true)
            @RequestBody MemberInquiryReq memberInquiryReq
    ){
        try{
            memberService.getMemberDatabase(memberInquiryReq);
            return responseService.singleResult(memberService.getMemberData(memberInquiryReq.getLoginId()));
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PostMapping(path = "/updatePassword")
    @ApiOperation(value = "2. 사용자 비밀번호 변경",notes = "사용자이전 비밀번호에서 새로운 비밀번호로 변경합니다.")
    public BaseResult updateUserPassword(
            @ApiParam(value = "비밀번호 변경 객체", required = true)
            @RequestBody MemberPasswordUpdateReq memberPasswordUpdateReq
    ){
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
