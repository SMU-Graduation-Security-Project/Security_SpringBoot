package com.EmperorPenguin.SangmyungBank.account.controller;

import com.EmperorPenguin.SangmyungBank.account.dto.AccountCreateReq;
import com.EmperorPenguin.SangmyungBank.account.dto.TransferReq;
import com.EmperorPenguin.SangmyungBank.account.service.AccountService;
import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.securityCard.dto.SecurityCardRandomRes;
import com.EmperorPenguin.SangmyungBank.securityCard.dto.SecurityCardValidReq;
import com.EmperorPenguin.SangmyungBank.securityCard.service.SecurityCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "02. 계좌")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/accounts")
public class AccountController {

    private final AccountService accountService;
    private final ResponseService responseService;
    private final SecurityCardService securityCardService;

    Map<String, SecurityCardRandomRes> validationSecurityCard = new HashMap<String, SecurityCardRandomRes>();
    Map<String, TransferReq> transferMap = new HashMap<String, TransferReq>();

    @PostMapping(path = "/add")
    @ApiOperation(value = "1. 계좌생성", notes = "사용자의 아이디와 비밀번호를 받아 사용자의 계좌를 생성합니다.")
    public BaseResult addAccount(
            @ApiParam (value = "계좌 객체", required = true)
            @RequestBody AccountCreateReq accountCreateReq
    ){
        try {
            accountService.createAccount(accountCreateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PostMapping(path = "/transaction/validateaccount")
    @ApiOperation(value="2. 계좌 이체(계좌 검증)", notes = "검증이 마무리되면 프론트로 번호를 전달합니다.")
    public BaseResult validAccount(
            @ApiParam (value = "계좌 이체 객체", required = true)
            @RequestBody TransferReq transferReq
    ){
        try {
            // 사용자 계좌 검증
            accountService.validationAccount(transferReq);
            transferMap.put(transferReq.getLoginId(),transferReq);

            // 사용자에게 검증을 할 번호를 전달
            SecurityCardRandomRes securityCardRandomRes = securityCardService.selectNumber();
            validationSecurityCard.put(transferReq.getLoginId(),securityCardRandomRes);
            return responseService.singleResult(securityCardRandomRes);
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PostMapping (path = "/transaction/checksecuritycard")
    @ApiOperation(value="2. 계좌 이체(보안카드 검증)", notes = "해시 데이터를 비교합니다.")
    public BaseResult transaction(
            @ApiParam (value = "보안카드 검증 객체", required = true)
            @RequestBody SecurityCardValidReq securityCardValidReq
    ){
        try {
            accountService.validSecurityCard(
                    validationSecurityCard.get(securityCardValidReq.getLoginId()), securityCardValidReq);
            validationSecurityCard.remove(securityCardValidReq.getLoginId());
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PostMapping (path = "/transaction/transfer")
    @ApiOperation(value="2. 계좌 이체", notes = "사용자 아이디와 이체할 계좌, 금액과 해당 계좌의 비밀번호를 받아 이체합니다.")
    public BaseResult transaction(
            @ApiParam (value = "계좌 이체 객체", required = true)
            @RequestParam String loginId
    ){
        try {
            accountService.transaction(transferMap.get(loginId));
            transferMap.remove(loginId);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping(path = "/inquiry")
    @ApiOperation(value = "3. 전계좌 조회", notes = "아이디에 해당 하는 모든 계좌를 받아옵니다.")
    public BaseResult inquiry(
            @ApiParam (value = "사용자 ID", required = true)
            @RequestParam("loginId") String loginId
    ) {
        try {
            return responseService.listResult(accountService.inquiry(loginId));
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping(path = "/name")
    @ApiOperation(value = "4. 사용자 조회", notes = "계좌에 해당 하는 사용자를 받아옵니다.")
    public BaseResult findName(
            @ApiParam (value = "계좌번호", required = true)
            @RequestParam("accountNumber") Long accountNumber
    ) {
        try {
            return responseService.singleResult(accountService.getName(accountNumber));
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

}
