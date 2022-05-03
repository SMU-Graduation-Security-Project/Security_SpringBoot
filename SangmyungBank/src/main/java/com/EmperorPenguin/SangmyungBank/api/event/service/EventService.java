package com.EmperorPenguin.SangmyungBank.api.event.service;

import com.EmperorPenguin.SangmyungBank.api.event.dto.EventDTO;
import com.EmperorPenguin.SangmyungBank.api.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public EventDTO insertEvent(EventDTO event) {
        return eventRepository.insertEvent(event);
    }

    public List<EventDTO> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    public EventDTO getEventById(Long id) {
        return eventRepository.getEventById(id);
    }

    public void updateEvent(Long id, EventDTO event) {
        eventRepository.updateEvent(id , event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteEvent(id);
    }
}
