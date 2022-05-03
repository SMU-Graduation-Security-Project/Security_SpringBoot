package com.EmperorPenguin.SangmyungBank.api.event.repository;

import com.EmperorPenguin.SangmyungBank.api.event.dto.EventDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {
    //db 연동하는 코드 ...!
    // JPA
    static public ArrayList<EventDTO> events;

    static {
        events = new ArrayList<>();
        events.add(new EventDTO("이벤트제목1","이벤트내용1"));
        events.add(new EventDTO("이벤트제목2","이벤트내용2"));
        events.add(new EventDTO("이벤트제목3","이벤트내용3"));
    }

    public EventDTO insertEvent(EventDTO event) {
        events.add(event);
        return event;
    }  //insert

    public List<EventDTO> getAllEvents() {
        return events;
    }

    public EventDTO getEventByTitle(String title) {
        return events.stream()
                .filter(eventDTO -> eventDTO.getTitle().equals(title))
                .findAny()
                .orElse(new EventDTO("",""));
    }

    public void updateContent(String title, EventDTO event) {
        events.stream()
                .filter(eventDTO -> eventDTO.getTitle().equals(title))
                .findAny()
                .orElse(new EventDTO("", ""))
                .setContent(event.getContent());
    }

    public void deleteEvent(String title) {
        events.removeIf(eventDTO -> eventDTO.getTitle().equals(title));
    }
}
