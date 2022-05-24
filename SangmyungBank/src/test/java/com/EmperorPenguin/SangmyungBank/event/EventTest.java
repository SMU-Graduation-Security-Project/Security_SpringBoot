package com.EmperorPenguin.SangmyungBank.event;

import com.EmperorPenguin.SangmyungBank.event.dto.EventCreateReq;
import com.EmperorPenguin.SangmyungBank.event.entity.Event;
import com.EmperorPenguin.SangmyungBank.event.repository.EventRepository;
import com.EmperorPenguin.SangmyungBank.event.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class EventTest {

    @Autowired
    EventService eventService;
    @Autowired
    EventRepository eventRepository;

    @Test
    void 이벤트생성() {

        // given
        String title = "이벤트제목30";
        String content = "이벤트내용";
        String startDate = "2022-05-24";
        String endDate = "2022-06-24";
//        String writeDate = "";

        EventCreateReq eventCreateReq = EventCreateReq.builder()
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        // when
        eventService.createEvent(eventCreateReq);
        Event findEvent = eventRepository.findByTitle(title).get();

        // then
        assertThat(findEvent.getTitle()).isEqualTo(title);
        assertThat(findEvent.getContent()).isEqualTo(content);
        assertThat(findEvent.getStartDate()).isEqualTo(startDate);
        assertThat(findEvent.getEndDate()).isEqualTo(endDate);
    }

//    @Test
//    void listAllDoingEvents() {
//    }
//
//    @Test
//    void listAllDoneEvents() {
//    }
//
//    @Test
//    void getSingleEvent() {
//    }
//
//    @Test
//    void updateEvent() {
//    }
//
//    @Test
//    void deleteEvent() {
//    }
}