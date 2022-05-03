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
    //C->POST
    //R->GET
    //U->PUT
    //D->DELETE

    @PostMapping("")
    public EventDTO insertEvent(@RequestBody EventDTO event) {
        return  eventService.insertEvent(event);
    }

    @GetMapping("")
    public List<EventDTO> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{title}")
    public EventDTO getEventByTitle(@PathVariable String title) {
        return eventService.getEventByTitle(title);
    }

    @PutMapping("/{title}")
    public void updateContent(@PathVariable String title,@RequestBody EventDTO event) {
        eventService.updateContent(title, event);
    }

    @DeleteMapping("/{title}")
    public void deleteEvent(@PathVariable String title) {
        eventService.deleteEvent(title);
    }
}