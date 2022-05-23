package com.EmperorPenguin.SangmyungBank.event.repository;


import com.EmperorPenguin.SangmyungBank.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByTitle(String title);


    @Query(value = "select * from event where timestampdiff(day, now(), end_Date)>=0", nativeQuery = true)
    List<Event> findRun();

    @Query(value = "select * from event where timestampdiff(day, now(), end_Date)<0", nativeQuery = true)
    List<Event> findDone();

    @Modifying(clearAutomatically = true)
    @Query("update News n set n.title = ?2, n.content = ?3 where n.id = ?1")
    void updateEvent(@Param("id")Long id, @Param("title") String title, @Param("content") String content);
}
