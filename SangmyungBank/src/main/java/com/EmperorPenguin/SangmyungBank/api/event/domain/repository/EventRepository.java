package com.EmperorPenguin.SangmyungBank.api.event.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.event.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> { }
