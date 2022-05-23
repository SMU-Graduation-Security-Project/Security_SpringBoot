package com.EmperorPenguin.SangmyungBank.news.repository;

import com.EmperorPenguin.SangmyungBank.news.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findByTitle(String title);

    @Modifying(clearAutomatically = true)
    @Query("update News n set n.title = ?2, n.content = ?3 where n.id = ?1")
    void updateNews(@Param("id")Long id, @Param("title") String title,@Param("content") String content);
}
