package com.EmperorPenguin.SangmyungBank.securitynotices.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.securitynotices.dto.SecurityNoticeCreateReq;
import com.EmperorPenguin.SangmyungBank.securitynotices.dto.SecurityNoticeUpdateReq;
import com.EmperorPenguin.SangmyungBank.securitynotices.service.SecurityNoticesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(tags="10. 보안공지")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/guest/cont")
public class SecurityNoticesController {

    private final SecurityNoticesService securityNoticesService;
    private final ResponseService responseService;

    @PostMapping("/security-notices")
    @ApiOperation(value="1. 보안공지 생성", notes = "보안공지 이름, 내용을 받아 저장합니다.")
    public BaseResult createNews(
            @ApiParam(value = "보안공지 객체", required = true)
            @RequestBody SecurityNoticeCreateReq securityNoticeCreateReq
    ) {
        try {
            securityNoticesService.createSecurityNotice(securityNoticeCreateReq);
            return responseService.successResult();
        } catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/security-notices")
    @ApiOperation(value="2. 보안공지 모두 가져오기",notes = "보안공지들을 전부 가져옵니다.")
    public BaseResult listAllSecurityNotices() {
        try {
            return responseService.listResult(securityNoticesService.getAllSecurityNotices());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/security-notices/{id}")
    @ApiOperation(value="3. 특정 보안공지 가져오기", notes = "PK에 해당하는 보안공지를 가져옵니다.")
    public BaseResult getNewsDetail(
            @ApiParam(value = "보안공지 ID", required = true)
            @PathVariable Long id
    ) {
        try {
            return responseService.singleResult(securityNoticesService.getSingleSecurityNotice(id).toDto());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PutMapping("/security-notices/{id}")
    @ApiOperation(value="4. 보안공지 업데이트", notes = "PK와 업데이트할 보안공지 내용을 받아 업데이트 합니다.")
    public BaseResult updateNews(
            @ApiParam(value = "변경된 보안공지 객체", required = true)
            @RequestBody SecurityNoticeUpdateReq securityNoticeUpdateReq
    ) {
        try {
            securityNoticesService.updateSecurityNotice(securityNoticeUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/security-notices/{id}")
    @ApiOperation(value="5. 보안공지 삭제", notes = "PK에 해당하는 보안공지를 삭제합니다.")
    public BaseResult deleteNews(
            @ApiParam(value = "보안공지 ID", required = true)
            @PathVariable Long id
    ) {
        try {
            securityNoticesService.deleteSecurityNotice(id);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

}
