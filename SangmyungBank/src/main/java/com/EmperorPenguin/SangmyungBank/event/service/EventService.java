package com.EmperorPenguin.SangmyungBank.event.service;

import com.EmperorPenguin.SangmyungBank.event.domain.event.Event;
import com.EmperorPenguin.SangmyungBank.event.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(@RequestBody Event event)
    {
        return eventRepository.save(event);
    }

    public List<Event> listAllEvents() {
        List<Event> eventList = eventRepository.findAll();
        if (eventList.isEmpty())
            return null;

        return eventList;
    }

    public Event getEventById(@PathVariable Long id) {
        Event event = eventRepository.findById(id)
                .orElse(null);

        return event;
    }

    public Event updateEvent(@PathVariable Long id, @RequestBody Event eventDetails){
        Event event = eventRepository.findById(id)
                        .orElse(null);
        if (event != null) {
            event.setTitle(eventDetails.getTitle());
            event.setContent(eventDetails.getContent());

            Event updateEvent = eventRepository.save(event);
            return updateEvent;
        }
        else {
            return null;
        }
    }

    public Event deleteEvent(@PathVariable Long id) {
        Event event = eventRepository.findById(id)
                .orElse(null);
        if (event != null) {
            eventRepository.delete(event);
            return event;
        }
        else {
            return null;
        }
    }

//    public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable Long id) {
//        Event event = eventRepository.findById(id)
//                .orElse(null);
//
//        eventRepository.delete(event);
//        Map <String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }

}
