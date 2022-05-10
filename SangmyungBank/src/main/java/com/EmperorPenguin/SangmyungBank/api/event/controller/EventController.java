package com.EmperorPenguin.SangmyungBank.api.event.controller;

import com.EmperorPenguin.SangmyungBank.api.event.domain.event.Event;
import com.EmperorPenguin.SangmyungBank.api.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cont")
public class EventController {


    private final EventService eventService;

    // create event
    @PostMapping("/event")
    public ResponseEntity<HttpStatus> createEvent(@RequestBody Event event) {
        event.setCreatedDate(LocalDateTime.now());
        Event resultEvent = eventService.createEvent(event);
        if(resultEvent == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
//    public Event createEvent(@RequestBody Event event)
//    {
//        event.setCreatedDate(LocalDateTime.now());
//        return eventService.createEvent(event);
//    }

    // list all events
    @GetMapping("/event")
    public ResponseEntity<List<Event>> listAllEvents() {
        List<Event> eventList = eventService.listAllEvents();
        if (eventList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(eventList);
        }
    }

    // get event by id
    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(event);
        }
    }

    // update event
    @PutMapping("/event/{id}")
    public ResponseEntity<HttpStatus> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event event = eventService.updateEvent(id, eventDetails);
        if (event == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }

    // delete event
    @DeleteMapping("/event/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id) {
        Event event = eventService.deleteEvent(id);
        if (event == null) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(HttpStatus.OK);
        }
    }
//    @DeleteMapping("/event/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable Long id) {
//        return eventService.deleteEvent(id);
//    }
}
