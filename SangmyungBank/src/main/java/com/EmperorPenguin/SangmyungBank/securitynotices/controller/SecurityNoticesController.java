package com.EmperorPenguin.SangmyungBank.securitynotices.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.securitynotices.dto.SecurityNoticeCreateReq;
import com.EmperorPenguin.SangmyungBank.securitynotices.dto.SecurityNoticeUpdateReq;
import com.EmperorPenguin.SangmyungBank.securitynotices.service.SecurityNoticesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(tags="보안공지 생성, 보안공지 모두 가져오기, 특정 보안공지 가져오기, 보안공지 업데이트, 보안공지 삭제")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cont")
public class SecurityNoticesController {

    private final SecurityNoticesService securityNoticesService;
    private final ResponseService responseService;

    @PostMapping("/security-notices")
    @ApiOperation(value="보안공지 생성")
    public BaseResult createNews(@RequestBody SecurityNoticeCreateReq securityNoticeCreateReq) {
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
    @ApiOperation(value="보안공지 모두 가져오기")
    public BaseResult listAllSecurityNotices() {
        try {
            return responseService.listResult(securityNoticesService.allSecurityNotices());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/security-notices/{id}")
    @ApiOperation(value="특정 보안공지 가져오기")
    public BaseResult getNewsDetail(@PathVariable Long id) {
        try {
            return responseService.singleResult(securityNoticesService.getSingleSecurityNotice(id).toDto());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PutMapping("/security-notices/{id}")
    @ApiOperation(value="보안공지 업데이트")
    public BaseResult updateNews(@RequestBody SecurityNoticeUpdateReq securityNoticeUpdateReq) {
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
    @ApiOperation(value="보안공지 삭제")
    public BaseResult deleteNews(@PathVariable Long id) {
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
