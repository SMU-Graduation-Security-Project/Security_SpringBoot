package com.EmperorPenguin.SangmyungBank.counsel.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselCreateReq;
import com.EmperorPenguin.SangmyungBank.counsel.dto.CounselUpdateReq;
import com.EmperorPenguin.SangmyungBank.counsel.service.CounselService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="11. 상담글")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user/cont")
public class CounselController {
    private final CounselService counselService;
    private final ResponseService responseService;

    @PostMapping("/counsels")
    @ApiOperation(value="1. 상담글 생성", notes = "사용자의 아이디와 상담글 이름, 내용을 받아 상담글을 생성")
    public BaseResult createCounsel(
            @ApiParam(value = "상담글 객체", required = true)
            @RequestBody CounselCreateReq counselCreateReq
    ) {
        try {
            counselService.createCounsel(counselCreateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/counsels")
    @ApiOperation(value="2. 개인의 상담글 모두 가져오기", notes = "상담글을 모두 가져옵니다.")
    public BaseResult listAllCounsel(
            @ApiParam(value = "사용자 ID", required = true)
            @RequestParam String loginId
    ) {
        try {
            return responseService
                    .listResult(counselService.listAllCounsel(loginId));
        }catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/counsels/{id}")
    @ApiOperation(value="3. 특정 상담글 가져오기", notes = "PK에 해당하는 상담글 하나를 가져옵니다.")
    public BaseResult getCounselDetail(
            @ApiParam(value = "상담글 객체 ID", required = true)
            @PathVariable Long id
    ) {
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

    @PutMapping("/counsels/{id}")
    @ApiOperation(value="4. 상담글 업데이트", notes = "상담글을 작성한 이가 상담들을 업데이트 할 내용을 적어 업데이트 합니다.")
    public BaseResult updateCounsel(
            @ApiParam(value = "변경된 상담글 객체", required = true)
            @RequestBody CounselUpdateReq counselUpdateReq
    ) {
        try {
            counselService.updateCounsel(counselUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/counsels/{id}")
    @ApiOperation(value="5. 상담글 삭제", notes = "특정 상담글을 쓴 이의 글을 삭제합니다.")
    public BaseResult deleteCounsel(
            @ApiParam(value = "상담글 객체 ID", required = true)
            @PathVariable Long id,
            @RequestParam String loginId
    ) {
        try {
            counselService.deleteCounsel(id, loginId);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
