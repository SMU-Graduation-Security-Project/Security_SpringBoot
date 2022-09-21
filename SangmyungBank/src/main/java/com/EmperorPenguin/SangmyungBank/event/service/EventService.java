package com.EmperorPenguin.SangmyungBank.event.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.BaseException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.event.dto.EventCreateReq;
import com.EmperorPenguin.SangmyungBank.event.dto.EventInquiryRes;
import com.EmperorPenguin.SangmyungBank.event.dto.EventUpdateReq;
import com.EmperorPenguin.SangmyungBank.event.entity.Event;
import com.EmperorPenguin.SangmyungBank.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public void createEvent(EventCreateReq eventCreateReq) {
        if (eventRepository.findByTitle(eventCreateReq.getTitle()).isPresent()) {
            throw new BaseException(ExceptionMessages.ERROR_EVENT_EXIST);
        }
        try {
            eventRepository.save(eventCreateReq.toEntity());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("이벤트 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<EventInquiryRes> listAllDoingEvents() {
        return eventRepository.findRun(new DateConfig().getDateTime())
                .stream()
                .sorted(Comparator.comparing(Event::getCreateDate).reversed())
                .map(Event::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<EventInquiryRes> listAllDoneEvents() {
        return eventRepository.findDone(new DateConfig().getDateTime())
                .stream()
                .sorted(Comparator.comparing(Event::getCreateDate).reversed())
                .map(Event::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Event getSingleEvent (Long id) {
        if(!eventRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_EVENT_NOT_EXIST);
        }
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new BaseException(ExceptionMessages.ERROR_UNDEFINED));
    }

    @Transactional
    public void updateEvent(EventUpdateReq eventUpdateReq) {
        if(!eventRepository.existsById(eventUpdateReq.getId())){
            throw new BaseException(ExceptionMessages.ERROR_EVENT_NOT_EXIST);
        }
        try {
            eventRepository.updateEvent(eventUpdateReq.getId(),eventUpdateReq.getTitle(),eventUpdateReq.getContent());
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("이벤트 업데이트에 실패했습니다.");
        }
    }

    @Transactional
    public void deleteEvent(Long id) {
        if(!eventRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_NEWS_NOT_EXIST);
        }
        try {
            eventRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("이벤트 삭제에 실패했습니다.");
        }
    }
}
