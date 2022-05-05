package com.EmperorPenguin.SangmyungBank.api.event.controller;

import com.EmperorPenguin.SangmyungBank.api.event.model.Event;
import com.EmperorPenguin.SangmyungBank.api.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cont")
public class EventController {

    @Autowired
    private EventService eventService;

    // create event
    @PostMapping("/event")
    public Event createEvent(@RequestBody Event event)
    {
        event.setCreatedDate(LocalDateTime.now());
        return eventService.createEvent(event);
    }

    // list all events
    @GetMapping("/event")
    public List<Event> listAllEvents() {
        return eventService.listAllEvents();
    }

    // get event by id
    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    // update event
    @PutMapping("/event/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id, @RequestBody Event eventDetails) {
        return eventService.updateEvent(id, eventDetails);
    }

    // delete event
    @DeleteMapping("/event/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }
}
