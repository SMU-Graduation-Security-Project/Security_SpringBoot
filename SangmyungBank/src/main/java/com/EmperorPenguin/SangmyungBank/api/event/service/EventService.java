package com.EmperorPenguin.SangmyungBank.api.event.service;

import com.EmperorPenguin.SangmyungBank.api.event.model.Event;
import com.EmperorPenguin.SangmyungBank.api.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    public List<Event> listAllEvents() {
        return eventRepository.findAll();
    }

    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventRepository.findById(id)
                .orElse(null);

        return ResponseEntity.ok(event);
    }

    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails){
        Event event = eventRepository.findById(id)
                        .orElse(null);

        event.setTitle(eventDetails.getTitle());
        event.setContent(eventDetails.getContent());

        Event updateEvent = eventRepository.save(event);
        return ResponseEntity.ok(updateEvent);
    }

    public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable Long id) {
        Event event = eventRepository.findById(id)
                .orElse(null);

        eventRepository.delete(event);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
