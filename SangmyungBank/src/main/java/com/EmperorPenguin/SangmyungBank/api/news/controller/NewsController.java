package com.EmperorPenguin.SangmyungBank.api.news.controller;

import com.EmperorPenguin.SangmyungBank.api.news.domain.news.News;
import com.EmperorPenguin.SangmyungBank.api.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cont")
public class NewsController {

    private final NewsService newsService;

    // create news
    @PostMapping("/news")
    public ResponseEntity<HttpStatus> createNews(@RequestBody News news) {
        news.setCreatedDate(LocalDateTime.now());
        newsService.createNews(news);
        return ResponseEntity.ok(HttpStatus.OK);
    }
//    public News createNews(@RequestBody News news) {
//        news.setCreatedDate(LocalDateTime.now());
//        return newsService.createNews(news);
//    }

    // list all news
    @GetMapping("/news")
    public ResponseEntity<List<News>> listAllNews() {
        List<News> newsList = newsService.listAllNews();
        if (newsList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(newsList);
        }
    }

    // get news by id
    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        News news = newsService.getNewsById(id);
        if (news == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(news);
        }
    }

    // update news
    @PutMapping("/news/{id}")
    public ResponseEntity<HttpStatus> updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        News news = newsService.updateNews(id, newsDetails);
        if (news == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    // delete news
    @DeleteMapping("/news/{id}")
    public ResponseEntity<HttpStatus> deleteNews(@PathVariable Long id) {
        News news = newsService.deleteNews(id);
        if (news == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
}
