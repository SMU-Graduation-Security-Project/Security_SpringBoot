package com.EmperorPenguin.SangmyungBank.transaction.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.transaction.dto.TransactionInquiryReq;
import com.EmperorPenguin.SangmyungBank.transaction.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Api(tags = "3. 거래 내역")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final ResponseService responseService;

    @PostMapping("/inquiry")
    @ApiOperation(value = "1. 계좌 거래내역 조회", notes = "특정 계좌의 거래내역을 전달합니다.")
    public BaseResult getTransactionData(@RequestBody TransactionInquiryReq transactionInquiryReq){
        try {
            return responseService.listResult(
                    Collections.reverse(transactionService.showTransactions(transactionInquiryReq))
            );
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
