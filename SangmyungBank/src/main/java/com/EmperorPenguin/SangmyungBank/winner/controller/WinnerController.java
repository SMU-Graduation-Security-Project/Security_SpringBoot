package com.EmperorPenguin.SangmyungBank.winner.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.winner.dto.WinnerCreateReq;
import com.EmperorPenguin.SangmyungBank.winner.dto.WinnerUpdateReq;
import com.EmperorPenguin.SangmyungBank.winner.service.WinnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="12. 당첨자발표")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/guest/cont/events")
public class WinnerController {

    private final WinnerService winnerService;
    private final ResponseService responseService;

    @PostMapping("/winner")
    @ApiOperation(value="1. 당첨자리스트 생성", notes = "이벤트 id, 당첨자목록을 받아 당첨자 리스트를 저장합니다.")
    public BaseResult createWinner(
            @ApiParam(value = "이벤트 당첨자 객체",required = true)
            @RequestBody WinnerCreateReq winnerCreateReq
    ) {
        try {
            winnerService.createWinner(winnerCreateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/winner")
    @ApiOperation(value="2. 당첨자 리스트 모두 가져오기", notes = "모든 당첨자 리스트를 가져옵니다.")
    public BaseResult listAllWinnerLists() {
        try {
            return responseService.listResult(winnerService.getAllWinnerLists());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/winner/{id}")
    @ApiOperation(value="3. 특정 당첨자 리스트 가져오기", notes = "PK에 해당하는 당첨자 리스트를 가져옵니다.")
    public BaseResult getWinnerDetail(
            @ApiParam(value = "당첨자이벤트 ID", required = true)
            @PathVariable Long id
    ) {
        try {
            return responseService.singleResult(
                    winnerService.getSingleWinner(id));
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PutMapping("/winner/{id}")
    @ApiOperation(value="4. 당첨자 리스트 업데이트", notes = "PK에 해당하는 당첨자 리스트를 업데이트 합니다.")
    public BaseResult updateWinner(
            @ApiParam(value = "변경된 이벤트 당첨자 객체", required = true)
            @RequestBody WinnerUpdateReq winnerUpdateReq
    ) {
        try {
            winnerService.updateWinner(winnerUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/winner/{id}")
    @ApiOperation(value="5. 당첨자 리스트 삭제", notes = "PK에 해당하는 당첨자 리스트를 삭제합니다.")
    public BaseResult deleteWinner(
            @ApiParam(value = "당첨자이벤트 ID", required = true)
            @PathVariable Long id
    ) {
        try {
            winnerService.deleteWinner(id);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
