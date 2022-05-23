package com.EmperorPenguin.SangmyungBank.counsel.repository;

import com.EmperorPenguin.SangmyungBank.counsel.entity.Counsel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CounselRepository extends JpaRepository<Counsel, Long> {
    Optional<Counsel> findByTitle(String title);

    @Modifying
    @Query("update Counsel n set n.title = ?2, n.content = ?3 where n.id = ?1")
    void updateCounsel(@Param("id")Long id, @Param("title") String title, @Param("content") String content);
}
