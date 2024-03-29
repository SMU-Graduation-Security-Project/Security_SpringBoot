package com.EmperorPenguin.SangmyungBank.card.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.card.dto.CardCreateReq;
import com.EmperorPenguin.SangmyungBank.card.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="05. 카드")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user/card")
public class CardController {
    private final CardService cardService;
    private final ResponseService responseService;

    @PostMapping("")
    @ApiOperation(value="1. 카드 생성", notes = "사용자의 아이디와 계좌번호, 카드종류를 받아 카드 생성")
    public BaseResult createCard(
            @ApiParam(value = "개인 카드 객체", required = true)
            @RequestBody CardCreateReq cardCreateReq
    ) {
        try {
            cardService.createCard(cardCreateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping(path = "/cardlist")
    @ApiOperation(value = "2. 카드 조회", notes = "아이디에 해당 하는 모든 카드를 받아옵니다.")
    public BaseResult cardList(
            @ApiParam(value = "사용자 ID", required = true)
            @RequestParam String loginId
    ) {
        try {
            return responseService.listResult(cardService.cardList(loginId));
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

}
