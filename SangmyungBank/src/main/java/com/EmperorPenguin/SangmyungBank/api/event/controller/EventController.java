package com.EmperorPenguin.SangmyungBank.api.event.controller;

import com.EmperorPenguin.SangmyungBank.api.event.dto.EventDTO;
import com.EmperorPenguin.SangmyungBank.api.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cont/event")
public class EventController {
    @Autowired
    EventService eventService;
    //CRUD

    @PostMapping("")
    public EventDTO insertEvent(@RequestBody EventDTO event) {
        return  eventService.insertEvent(event);
    }

    @GetMapping("")
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDTO getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable Long id,@RequestBody EventDTO event) {
        eventService.updateEvent(id , event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}