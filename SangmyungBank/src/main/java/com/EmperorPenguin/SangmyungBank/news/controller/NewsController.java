package com.EmperorPenguin.SangmyungBank.news.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.news.dto.NewsCreateReq;
import com.EmperorPenguin.SangmyungBank.news.dto.NewsUpdateReq;
import com.EmperorPenguin.SangmyungBank.news.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags="08. 새소식")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cont")
public class NewsController {

    private final NewsService newsService;
    private final ResponseService responseService;

    @PostMapping("/news")
    @ApiOperation(value="1. 새소식 생성", notes = "새소식 이름과 내용을 받아 새소식을 저장합니다.")
    public BaseResult createNews(@RequestBody NewsCreateReq newsCreateReq) {
        try {
            newsService.createNews(newsCreateReq);
            return responseService.successResult();
        } catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/news")
    @ApiOperation(value="2. 새소식 모두 가져오기", notes = "모든 새소식을 가져옵니다.")
    public BaseResult listAllNews() {
        try {
            return responseService.listResult(newsService.getAllNews());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/news/{id}")
    @ApiOperation(value="3. 특정 새소식 가져오기", notes = "PK에 해당하는 새소식을 가져옵니다.")
    public BaseResult getNewsDetail(@PathVariable Long id) {
        try {
            return responseService.singleResult(newsService.getSingleNews(id).toDto());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @PutMapping("/news/{id}")
    @ApiOperation(value="4. 새소식 업데이트", notes = "PK에 해당하는 새소식의 정보를 업데이트 합니다.")
    public BaseResult updateNews(@RequestBody NewsUpdateReq newsUpdateReq) {
        try {
            newsService.updateNews(newsUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @DeleteMapping("/news/{id}")
    @ApiOperation(value="5. 새소식 삭제", notes = "PK에 해당하는 새소식을 제거합니다.")
    public BaseResult deleteNews(@PathVariable Long id) {
        try {
            newsService.deleteNews(id);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}
