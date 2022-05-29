package com.EmperorPenguin.SangmyungBank.cardlist.repository;

import com.EmperorPenguin.SangmyungBank.cardlist.entity.CardList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CardListRepository extends JpaRepository<CardList, Long> {
    Optional<CardList> findByTitle(String title);

    @Modifying(clearAutomatically = true)
    @Query("update CardList n set n.title = ?2, n.content = ?3 where n.id = ?1")
    void updateCardList(@Param("id")Long id, @Param("title") String title, @Param("content") String content);
}
