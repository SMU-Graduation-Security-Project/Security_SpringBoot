package com.EmperorPenguin.SangmyungBank.account.controller;

import com.EmperorPenguin.SangmyungBank.account.dto.AccountCreateReq;
import com.EmperorPenguin.SangmyungBank.account.dto.TransferReq;
import com.EmperorPenguin.SangmyungBank.account.service.AccountService;
import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "03. 계좌")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/accounts")
public class AccountController {
    private final AccountService accountService;
    private final ResponseService responseService;

    @PostMapping(path = "/add")
    @ApiOperation(value = "1. 계좌생성", notes = "사용자의 아이디와 비밀번호를 받아 사용자의 계좌를 생성합니다.")
    public BaseResult addAccount(@ApiParam @RequestBody AccountCreateReq accountCreateReq){
        try {
            accountService.createAccount(accountCreateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PostMapping(path = "/transaction")
    @ApiOperation(value="2. 계좌 이체", notes = "사용자 아이디와 이체할 계좌, 금액과 해당 계좌의 비밀번호를 받아 이체합니다.")
    public BaseResult transaction(@ApiParam @RequestBody TransferReq transferReq){
        try {
            accountService.transaction(transferReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping(path = "/inquiry")
    @ApiOperation(value = "3. 전계좌 조회", notes = "아이디에 해당 하는 모든 계좌를 받아옵니다.")
    public BaseResult inquiry(@ApiParam @RequestParam String userId) {
        try {
            return responseService.listResult(accountService.inquiry(userId));
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
