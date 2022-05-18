package com.EmperorPenguin.SangmyungBank.account.controller;

import com.EmperorPenguin.SangmyungBank.account.dto.AccountCreateReq;
import com.EmperorPenguin.SangmyungBank.account.dto.TransactionReq;
import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.account.repository.AccountRepository;
import com.EmperorPenguin.SangmyungBank.account.service.AccountService;
import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "계좌 생성, 계좌이체, 전계좌 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/accounts")
public class AccountController {
    private final AccountService accountService;
    private final ResponseService responseService;

    @PostMapping(path = "/add")
    @ApiOperation(value = "계좌생성")
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
    @ApiOperation(value="계좌 이체")
    public BaseResult transaction(@ApiParam @RequestBody TransactionReq transactionReq){
        try {
            accountService.transaction(transactionReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping(path = "/inquiry")
    @ApiOperation(value = "전계좌 조회")
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
