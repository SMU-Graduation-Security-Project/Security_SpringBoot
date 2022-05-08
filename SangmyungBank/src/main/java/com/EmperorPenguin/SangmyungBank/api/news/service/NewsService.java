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

    public News getNewsById(@PathVariable Long id) {
        News news = newsRepository.findById(id)
                .orElse(null);

        return news;
    }

    public News updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        News news = newsRepository.findById(id)
                .orElse(null);

        news.setTitle(newsDetails.getTitle());
        news.setContent(newsDetails.getContent());

        News updateNews = newsRepository.save(news);
        return updateNews;
    }

    public News deleteNews(@PathVariable Long id) {
        News news = newsRepository.findById(id)
                .orElse(null);

        if (news != null) {
            newsRepository.delete(news);
            return news;
        } else {
            return null;
        }
    }
}
