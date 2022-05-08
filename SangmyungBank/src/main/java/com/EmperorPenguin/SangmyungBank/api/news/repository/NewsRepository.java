package com.EmperorPenguin.SangmyungBank.api.news.repository;

import com.EmperorPenguin.SangmyungBank.api.event.model.Event;
import com.EmperorPenguin.SangmyungBank.api.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> { }
