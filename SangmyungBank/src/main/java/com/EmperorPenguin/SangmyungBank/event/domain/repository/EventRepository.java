package com.EmperorPenguin.SangmyungBank.event.domain.repository;


import com.EmperorPenguin.SangmyungBank.event.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> { }
