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

@Api(tags="새소식 생성, 모든 새소식 가저오기, 특정 새소식 가져오기, 새소식 업데이트, 새소식 삭제")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cont")
public class NewsController {

    private final NewsService newsService;
    private final ResponseService responseService;

    @PostMapping("/news")
    @ApiOperation(value="새소식 생성")
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
    @ApiOperation(value="새소식 모두 가져오기")
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
    @ApiOperation(value="특정 새소식 가져오기")
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
    @ApiOperation(value="새소식 업데이트")
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
    @ApiOperation(value="새소식 삭제")
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
