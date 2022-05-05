package com.EmperorPenguin.SangmyungBank.api.news.controller;

import com.EmperorPenguin.SangmyungBank.api.news.model.News;
import com.EmperorPenguin.SangmyungBank.api.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cont")
public class NewsController {

    @Autowired
    private NewsService newsService;

    // create news
    @PostMapping("/news")
    public News createNews(@RequestBody News news)
    {
        news.setCreatedDate(LocalDateTime.now());
        return newsService.createNews(news);
    }

    // list all news
    @GetMapping("/news")
    public List<News> listAllNews() {
        return newsService.listAllNews();
    }

    // get news by id
    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        return newsService.getNewsById(id);
    }

    // update news
    @PutMapping("/news/{id}")
    public ResponseEntity<News> updateNews(
            @PathVariable Long id, @RequestBody News newsDetails) {
        return newsService.updateNews(id, newsDetails);
    }

    // delete news
    @DeleteMapping("/news/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteNews(@PathVariable Long id) {
        return newsService.deleteNews(id);
    }
}
