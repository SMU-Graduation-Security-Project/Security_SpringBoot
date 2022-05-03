package com.EmperorPenguin.SangmyungBank.api.event.service;

import com.EmperorPenguin.SangmyungBank.api.event.dto.EventDTO;
import com.EmperorPenguin.SangmyungBank.api.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    public EventDTO insertEvent(EventDTO event) {
        return eventRepository.insertEvent(event);
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    public EventDTO getEventByTitle(String title) {
        return eventRepository.getEventByTitle(title);
    }

    public void updateContent(String title, EventDTO event) {
        eventRepository.updateContent(title, event);
    }

    public void deleteEvent(String title) {
        eventRepository.deleteEvent(title);
    }
}
