package com.EmperorPenguin.SangmyungBank.news.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.NewsException;
import com.EmperorPenguin.SangmyungBank.news.dto.NewsCreateReq;
import com.EmperorPenguin.SangmyungBank.news.dto.NewsRequestRes;
import com.EmperorPenguin.SangmyungBank.news.dto.NewsUpdateReq;
import com.EmperorPenguin.SangmyungBank.news.entity.News;
import com.EmperorPenguin.SangmyungBank.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    @Transactional
    public void createNews(NewsCreateReq newsCreateReq) {
        if(newsRepository.findByTitle(newsCreateReq.getTitle()).isPresent()){
            throw new NewsException(ExceptionMessages.ERROR_NEWS_EXIST);
        }
        try{
            newsRepository.save(newsCreateReq.toEntity());
        }catch (Exception e){
            e.printStackTrace();
            throw new NewsException("새소식 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<NewsRequestRes> getAllNews() {
        return newsRepository.findAll()
                .stream()
                .map(News::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public News getSingleNews(Long id) {
        if(!newsRepository.existsById(id)){
            throw new NewsException(ExceptionMessages.ERROR_NEWS_NOT_EXIST);
        }
        return newsRepository
                .findById(id)
                .orElseThrow(() -> new NewsException(ExceptionMessages.ERROR_UNDEFINED));
    }

    @Transactional
    public void updateNews(NewsUpdateReq newsUpdateReq) {
        if(!newsRepository.existsById(newsUpdateReq.getId())){
            throw new NewsException(ExceptionMessages.ERROR_NEWS_NOT_EXIST);
        }
        try {
            newsRepository.updateNews(newsUpdateReq.getId(),newsUpdateReq.getTitle(),newsUpdateReq.getContent());
        }catch (Exception e){
            e.printStackTrace();
            throw new NewsException("새소식 업데이트에 실패했습니다.");
        }

    }

    @Transactional
    public void deleteNews(Long id) {
        if(!newsRepository.existsById(id)){
            throw new NewsException(ExceptionMessages.ERROR_NEWS_NOT_EXIST);
        }
        try{
            newsRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new NewsException("새소식 삭제에 실패했습니다.");
        }
    }
}
