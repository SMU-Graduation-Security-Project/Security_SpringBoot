package com.EmperorPenguin.SangmyungBank.news.service;

import com.EmperorPenguin.SangmyungBank.news.domain.news.News;
import com.EmperorPenguin.SangmyungBank.news.domain.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public News createNews(@RequestBody News news) {

        return newsRepository.save(news);
    }

    public List<News> listAllNews()
    {
        List<News> newsList = newsRepository.findAll();
        if(newsList.isEmpty())
            return null;

        return newsList;
    }

    public News getNewsById(@PathVariable Long id) {
        News news = newsRepository.findById(id)
                .orElse(null);

        return news;
    }

    public News updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        News news = newsRepository.findById(id)
                .orElse(null);
        if (news != null) {
            news.setTitle(newsDetails.getTitle());
            news.setContent(newsDetails.getContent());

            News updateNews = newsRepository.save(news);
            return updateNews;
        }
        else {
            return null;
        }
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
