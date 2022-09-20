//package com.EmperorPenguin.SangmyungBank.securityCard.controller;
//
//import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
//import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
//import com.EmperorPenguin.SangmyungBank.securityCard.service.SecurityCardService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@Api(tags="02.보안카드")
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("api/v1/user/securitycard")
//public class SecurityCardController {
//    private final SecurityCardService securityCardService;
//    private final ResponseService responseService;
//
//    @PostMapping("")
//    @ApiOperation(value="1. 보안카드 발급", notes = "사용자의 아이디로 보안카드 생성")
//    public BaseResult createOtpCard(
//            @ApiParam(value = "사용자 ID", required = true)
//            @RequestBody String loginId
//    ) {
//        try {
//            securityCardService.createSecurityCard(loginId);
//            return responseService.successResult();
//        }catch (Exception e){
//            return responseService.failResult(
//                    e.getMessage()
//            );
//        }
//    }
//
//    @GetMapping(path = "/view")
//    @ApiOperation(value = "2. 보안카드 조회", notes = "아이디에 해당 하는 보안카드를 조회합니다.")
//    public BaseResult securityCardCardList(
//            @ApiParam(value = "사용자 ID", required = true)
//            @RequestParam String loginId
//    ) {
//        try {
//            return responseService.singleResult(securityCardService.getSecurityCardData(loginId));
//        }catch (Exception e){
//            return responseService.failResult(
//                    e.getMessage());
//        }
//    }
//
//}
