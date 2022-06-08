package com.EmperorPenguin.SangmyungBank.cardlist.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListCreateReq;
import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListUpdateReq;
import com.EmperorPenguin.SangmyungBank.cardlist.service.CardListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="5. 카드 목록")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users/cardlist")
public class CardListController {

    private final CardListService cardListService;
    private final ResponseService responseService;

    @PostMapping("")
    @ApiOperation(value="1. 카드목록 생성")
    public BaseResult createCardList(@RequestBody CardListCreateReq cardListCreateReq) {
        try {
            cardListService.createCardList(cardListCreateReq);
            return responseService.successResult();
        } catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("")
    @ApiOperation(value="2. 카드목록 모두 가져오기")
    public BaseResult listAllCardLists() {
        try {
            return responseService.listResult(cardListService.getAllCardLists());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value="3. 특정 카드목록 가져오기")
    public BaseResult getCardListDetail(@PathVariable Long id) {
        try {
            return responseService.singleResult(cardListService.getSingleCardList(id).toDto());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value="4. 카드목록 업데이트")
    public BaseResult updateCardList(@RequestBody CardListUpdateReq cardListUpdateReq) {
        try {
            cardListService.updateCardList(cardListUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="5. 카드목록 삭제")
    public BaseResult deleteCardList(@PathVariable Long id) {
        try {
            cardListService.deleteCardList(id);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
