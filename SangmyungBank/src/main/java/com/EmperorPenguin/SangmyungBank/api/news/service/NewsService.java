package com.EmperorPenguin.SangmyungBank.api.news.service;

import com.EmperorPenguin.SangmyungBank.api.news.model.News;
import com.EmperorPenguin.SangmyungBank.api.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News createNews(@RequestBody News news) {
        return newsRepository.save(news);
    }

    public List<News> listAllNews() {
        return newsRepository.findAll();
    }

    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        News news = newsRepository.findById(id)
                .orElse(null);

        return ResponseEntity.ok(news);
    }

    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        News news = newsRepository.findById(id)
                .orElse(null);

        news.setTitle(newsDetails.getTitle());
        news.setContent(newsDetails.getContent());

        News updateNews = newsRepository.save(news);
        return ResponseEntity.ok(updateNews);
    }

    public ResponseEntity<Map<String, Boolean>> deleteNews(@PathVariable Long id) {
        News news = newsRepository.findById(id)
                .orElse(null);

        newsRepository.delete(news);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
