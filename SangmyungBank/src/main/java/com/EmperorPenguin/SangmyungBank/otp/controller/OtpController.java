//package com.EmperorPenguin.SangmyungBank.otp.controller;
//
//import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
//import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
//import com.EmperorPenguin.SangmyungBank.otp.service.OtpService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@Api(tags="02.OTP")
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("api/v1/user/otp")
//public class OtpController {
//    private final OtpService otpService;
//    private final ResponseService responseService;
//
//    @PostMapping("")
//    @ApiOperation(value="1. OTP 발급", notes = "사용자의 아이디로 OTP 생성")
//    public BaseResult createOtpCard(
//            @ApiParam(value = "사용자 ID", required = true)
//            @RequestBody String loginId
//    ) {
//        try {
//            otpService.createOtp(loginId);
//            return responseService.successResult();
//        }catch (Exception e){
//            return responseService.failResult(
//                    e.getMessage()
//            );
//        }
//    }
//
//    @GetMapping(path = "/view")
//    @ApiOperation(value = "2. OTP 조회", notes = "아이디에 해당 하는 OTP를 조회합니다.")
//    public BaseResult otpCardList(
//            @ApiParam(value = "사용자 ID", required = true)
//            @RequestParam String loginId
//    ) {
//        try {
//            return responseService.singleResult(otpService.getOtpData(loginId));
//        }catch (Exception e){
//            return responseService.failResult(
//                    e.getMessage());
//        }
//    }
//
//}
