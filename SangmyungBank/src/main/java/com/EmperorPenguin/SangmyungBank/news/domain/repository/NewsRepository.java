package com.EmperorPenguin.SangmyungBank.news.domain.repository;

import com.EmperorPenguin.SangmyungBank.news.domain.news.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> { }
