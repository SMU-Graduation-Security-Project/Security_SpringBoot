package com.EmperorPenguin.SangmyungBank.api.event.repository;

import com.EmperorPenguin.SangmyungBank.api.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> { }
