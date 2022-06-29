package com.EmperorPenguin.SangmyungBank.loan.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.loan.dto.LoanCreateReq;
import com.EmperorPenguin.SangmyungBank.loan.service.LoanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="07. 대출")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users/loans")
public class LoanController {
    private final LoanService loanService;
    private final ResponseService responseService;

    @PostMapping("")
    @ApiOperation(value="1. 대출 신청", notes = "사용자의 아이디와 계좌번호, 대출종류, 대출금액을 받아 대출")
    public BaseResult createLoan(@RequestBody LoanCreateReq loanCreateReq) {
        try {
            loanService.createLoan(loanCreateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping(path = "/loanlist")
    @ApiOperation(value = "2. 대출 조회", notes = "아이디에 해당 하는 모든 대출을 받아옵니다.")
    public BaseResult loanList(@ApiParam @RequestParam String loginId) {
        try {
            return responseService.listResult(loanService.loanList(loginId));
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
