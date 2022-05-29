package com.EmperorPenguin.SangmyungBank.transaction.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.transaction.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "이체 내역 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final ResponseService responseService;

    @GetMapping("/{accountNumber}")
    @ApiOperation(value = "계좌 거래내역 조회", notes = "특정 계좌의 거래내역을 전달합니다.")
    public BaseResult getTransactionData(@PathVariable Long accountNumber){
        try {
            return responseService.listResult(
                    transactionService.showTransactions(accountNumber)
            );
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
