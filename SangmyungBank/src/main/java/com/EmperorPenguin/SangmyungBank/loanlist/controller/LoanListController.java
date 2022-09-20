package com.EmperorPenguin.SangmyungBank.loanlist.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.loanlist.dto.LoanListCreateReq;
import com.EmperorPenguin.SangmyungBank.loanlist.dto.LoanListUpdateReq;
import com.EmperorPenguin.SangmyungBank.loanlist.service.LoanListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="06. 대출 목록")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/guest/loanlist")
public class LoanListController {

    private final LoanListService loanListService;
    private final ResponseService responseService;

    @PostMapping("")
    @ApiOperation(value="1. 대출목록 생성")
    public BaseResult createLoanList(
            @ApiParam(value = "대출 객체", required = true)
            @RequestBody LoanListCreateReq loanListCreateReq
    ) {
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
    public BaseResult getLoanListDetail(
            @ApiParam(value = "대출 객체 ID", required = true)
            @PathVariable Long id
    ) {
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
    public BaseResult updateLoanList(
            @ApiParam(value = "변경된 대출목록 객체", required = true)
            @RequestBody LoanListUpdateReq loanListUpdateReq
    ) {
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
    public BaseResult deleteLoanList(
            @ApiParam(value = "대출 객체", required = true)
            @PathVariable Long id
    ) {
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
