package com.EmperorPenguin.SangmyungBank.winner.repository;

import com.EmperorPenguin.SangmyungBank.event.entity.Event;
import com.EmperorPenguin.SangmyungBank.winner.entity.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WinnerRepository extends JpaRepository<Winner, Long> {

    Optional<Winner> findByEventId(Event eventId);

    @Modifying(clearAutomatically = true)
    @Query("update Winner n set  n.content = ?2 where n.id = ?1")
    void updateWinner(@Param("id")Long id, @Param("content") String content);
}
