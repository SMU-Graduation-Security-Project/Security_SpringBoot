package com.EmperorPenguin.SangmyungBank.event;

import com.EmperorPenguin.SangmyungBank.event.dto.EventCreateReq;
import com.EmperorPenguin.SangmyungBank.event.dto.EventInquiryRes;
import com.EmperorPenguin.SangmyungBank.event.dto.EventUpdateReq;
import com.EmperorPenguin.SangmyungBank.event.entity.Event;
import com.EmperorPenguin.SangmyungBank.event.repository.EventRepository;
import com.EmperorPenguin.SangmyungBank.event.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

    @Test
    void 진행중인이벤트목록() {

        //given
        String title1 = "진행중인이벤트30";
        String content1 = "이벤트내용";
        String startDate1 = "2022-05-24";
        String endDate1 = "2022-07-20";

        String title2 = "진행중인이벤트31";
        String content2 = "이벤트내용";
        String startDate2 = "2022-05-24";
        String endDate2 = "2022-07-20";

        String title3 = "종료된이벤트30";
        String content3 = "이벤트내용";
        String startDate3 = "2022-05-24";
        String endDate3 = "2022-05-27";

        EventCreateReq run1 = EventCreateReq.builder()
                .title(title1)
                .content(content1)
                .startDate(startDate1)
                .endDate(endDate1)
                .build();

        EventCreateReq run2 = EventCreateReq.builder()
                .title(title2)
                .content(content2)
                .startDate(startDate2)
                .endDate(endDate2)
                .build();

        EventCreateReq done1 = EventCreateReq.builder()
                .title(title3)
                .content(content3)
                .startDate(startDate3)
                .endDate(endDate3)
                .build();
        //when
        eventService.createEvent(run1);
        eventService.createEvent(run2);
        eventService.createEvent(done1);
        List<EventInquiryRes> list = eventService.listAllDoingEvents();

        //then
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void 종료된이벤트목록() {

        //given
        String title1 = "진행중인이벤트30";
        String content1 = "이벤트내용";
        String startDate1 = "2022-05-24";
        String endDate1 = "2022-07-20";

        String title2 = "진행중인이벤트31";
        String content2 = "이벤트내용";
        String startDate2 = "2022-05-24";
        String endDate2 = "2022-07-20";

        String title3 = "종료된이벤트30";
        String content3 = "이벤트내용";
        String startDate3 = "2022-05-24";
        String endDate3 = "2022-05-27";

        EventCreateReq run1 = EventCreateReq.builder()
                .title(title1)
                .content(content1)
                .startDate(startDate1)
                .endDate(endDate1)
                .build();

        EventCreateReq run2 = EventCreateReq.builder()
                .title(title2)
                .content(content2)
                .startDate(startDate2)
                .endDate(endDate2)
                .build();

        EventCreateReq done1 = EventCreateReq.builder()
                .title(title3)
                .content(content3)
                .startDate(startDate3)
                .endDate(endDate3)
                .build();
        //when
        eventService.createEvent(run1);
        eventService.createEvent(run2);
        eventService.createEvent(done1);
        List<EventInquiryRes> list = eventService.listAllDoneEvents();

        //then
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void 특정이벤트가져오기() {
        //given
        Event event = eventRepository.save(Event.builder()
                .title("진행중인이벤트30")
                .content("내용")
                .startDate("2022-05-24")
                .endDate("2022-07-20")
                .build());

        Event event2 = eventRepository.save(Event.builder()
                .title("진행중인이벤트31")
                .content("내용")
                .startDate("2022-05-24")
                .endDate("2022-07-20")
                .build());

        Long updateId = event.getId();
        Long updateId2 = event2.getId();

        //when
        Event single = eventService.getSingleEvent(updateId2);

        //then
        assertThat(single.getTitle()).isEqualTo(event2.getTitle());
    }

    @Test
    void 이벤트업데이트() {
        //given
        Event event = eventRepository.save(Event.builder()
                .title("진행중인이벤트30")
                .content("내용")
                .startDate("2022-05-24")
                .endDate("2022-07-20")
                .build());

        Long updateId = event.getId();
        String updateTitle = "수정된이벤트";
        String updateContent = "수정된내용";
        String updateStartDate = "2022-05-23";
        String updateEndDate = "2022-08-20";

        EventUpdateReq updateReq = EventUpdateReq.builder()
                .id(updateId)
                .content(updateContent)
                .startDate(updateStartDate)
                .endDate(updateEndDate)
                .title(updateTitle)
                .build();
        //when
//        eventRepository.updateEvent(updateId,updateTitle,updateContent);
        eventService.updateEvent(updateReq);
        Optional<Event> findEvent = eventRepository.findById(updateId);
        //then
        assertThat(findEvent.get().getTitle()).isEqualTo(updateTitle);
        assertThat(findEvent.get().getContent()).isEqualTo(updateContent);
        assertThat(findEvent.get().getStartDate()).isEqualTo(updateStartDate);
        assertThat(findEvent.get().getEndDate()).isEqualTo(updateEndDate);

    }

    @Test
    void 이벤트삭제() {
        //given
        Event event = eventRepository.save(Event.builder()
                .title("진행중인이벤트30")
                .content("내용")
                .startDate("2022-05-24")
                .endDate("2022-07-20")
                .build());

        Long updateId = event.getId();
        //when
        List<EventInquiryRes> list = eventService.listAllDoingEvents();
        eventService.deleteEvent(updateId);
        List<EventInquiryRes> list2 = eventService.listAllDoingEvents();
//        eventService.deleteEvent(updateId);
//        Optional<Event> findEvent = eventRepository.findById(updateId);
        //then
        assertThat(list.size()-1).isEqualTo(list2.size());
    }
}