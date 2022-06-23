package com.EmperorPenguin.SangmyungBank.loanlist.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListCreateReq;
import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListUpdateReq;
import com.EmperorPenguin.SangmyungBank.loanlist.dto.LoanListCreateReq;
import com.EmperorPenguin.SangmyungBank.loanlist.dto.LoanListUpdateReq;
import com.EmperorPenguin.SangmyungBank.loanlist.service.LoanListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="11. 대출 목록")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users/loanlist")
public class LoanListController {

    private final LoanListService loanListService;
    private final ResponseService responseService;

    @PostMapping("")
    @ApiOperation(value="1. 대출목록 생성")
    public BaseResult createLoanList(@RequestBody LoanListCreateReq loanListCreateReq) {
        try {
            loanListService.createLoanList(loanListCreateReq);
            return responseService.successResult();
        } catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("")
    @ApiOperation(value="2. 대출목록 모두 가져오기")
    public BaseResult listAllCardLists() {
        try {
            return responseService.listResult(loanListService.getAllLoanLists());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value="3. 특정 대출목록 가져오기")
    public BaseResult getLoanListDetail(@PathVariable Long id) {
        try {
            return responseService.singleResult(loanListService.getSingleLoanList(id).toDto());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value="4. 대출목록 업데이트")
    public BaseResult updateLoanList(@RequestBody LoanListUpdateReq loanListUpdateReq) {
        try {
            loanListService.updateLoanList(loanListUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="5. 대출목록 삭제")
    public BaseResult deleteLoanList(@PathVariable Long id) {
        try {
            loanListService.deleteLoanList(id);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
