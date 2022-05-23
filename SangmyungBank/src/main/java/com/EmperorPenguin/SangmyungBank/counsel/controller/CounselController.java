package com.EmperorPenguin.SangmyungBank.counsel.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselCreateReq;
import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselUpdateReq;
import com.EmperorPenguin.SangmyungBank.counsel.service.CounselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="상담글 생성, 모든 상담글 가져오기 ,특정 상담글 가져오기, 상담글 업데이트, 상담글 삭제")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cont")
public class CounselController {
    private final CounselService counselService;
    private final ResponseService responseService;

    @PostMapping("/counsel")
    @ApiOperation(value="상담글 생성")
    public BaseResult createCounsel(@RequestBody CounselCreateReq counselCreateReq) {
        try {
            counselService.createCounsel(counselCreateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/counsel")
    @ApiOperation(value="상담글 모두 가져오기")
    public BaseResult listAllCounsel() {
        try {
            return responseService
                    .listResult(counselService.listAllCounsel());
        }catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/counsel/{id}")
    @ApiOperation(value="특정 상담글 가져오기")
    public BaseResult getCounselDetail(@PathVariable Long id) {
        try {
            return responseService.singleResult(
                    counselService.getSingleCounsel(id).toDto()
            );
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PutMapping("/counsel/{id}")
    @ApiOperation(value="상담글 업데이트")
    public BaseResult updateCounsel(@RequestBody CounselUpdateReq counselUpdateReq) {
        try {
            counselService.updateCounsel(counselUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/counsel/{id}")
    @ApiOperation(value="상담글 삭제")
    public BaseResult deleteCounsel(@PathVariable Long id) {
        try {
            counselService.deleteCounsel(id);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
