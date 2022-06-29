package com.EmperorPenguin.SangmyungBank.otp.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.otp.dto.OtpCreateReq;
import com.EmperorPenguin.SangmyungBank.otp.service.OtpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="12.OTP")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users/otp")
public class OtpController {
    private final OtpService otpService;
    private final ResponseService responseService;

    @PostMapping("")
    @ApiOperation(value="1. OTP 발급", notes = "사용자의 아이디로 OTP 생성")
    public BaseResult createCard(@RequestBody OtpCreateReq otpCreateReq) {
        try {
            otpService.createOtp(otpCreateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping(path = "/view")
    @ApiOperation(value = "2. OTP 조회", notes = "아이디에 해당 하는 OTP를 조회합니다.")
    public BaseResult cardList(@ApiParam @RequestParam String loginId) {
        try {
            return responseService.listResult(otpService.otpList(loginId));
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage());
        }
    }

}
