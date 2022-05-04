package com.EmperorPenguin.SangmyungBank.api.event.repository;

import com.EmperorPenguin.SangmyungBank.api.event.dto.EventDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {

    static public ArrayList<EventDTO> events;

    static {
        events = new ArrayList<>();               // db에 저장하는법?
    }
    //이벤트 추가
    public EventDTO insertEvent(EventDTO event) {
        events.add(event);
        return event;
    }
    //전체 이벤트리스트
    public List<EventDTO> getAllEvents() {
        return events;
    }
    //id로 이벤트찾기
    public EventDTO getEventById(Long id) {
        return events.stream()
                .filter(eventDTO -> eventDTO.getId().equals(id))
                .findAny()
                .orElse(new EventDTO());
    }
    //이벤트 수정
    public void updateEvent(Long id, EventDTO event) {
        events.stream()
                .filter(eventDTO -> eventDTO.getId().equals(id))
                .findAny()
                .orElse(new EventDTO())
                .setContent(event.getContent());     // 제목도 바꾸는법?
    }
    //이벤트 삭제
    public void deleteEvent(Long id) {
        events.removeIf(eventDTO -> eventDTO.getId().equals(id));
    }
}
