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

    @Query("select e from Event  e where e.endDate > ?1")
    List<Event> findRun(@Param("dateTime")String dateTime);

    @Query("select e from Event  e where e.endDate < ?1")
    List<Event> findDone(@Param("dateTime")String dateTime);

    @Modifying(clearAutomatically = true)
    @Query("update Event e set e.title = ?2, e.content = ?3 where e.id = ?1")
    void updateEvent(@Param("id")Long id, @Param("title") String title, @Param("content") String content);
}
