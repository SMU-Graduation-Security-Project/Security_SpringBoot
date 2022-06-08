package com.EmperorPenguin.SangmyungBank.event.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.event.dto.EventCreateReq;
import com.EmperorPenguin.SangmyungBank.event.dto.EventUpdateReq;
import com.EmperorPenguin.SangmyungBank.event.entity.Event;
import com.EmperorPenguin.SangmyungBank.event.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Api(tags="7. 이벤트")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cont")
public class EventController {

    private final EventService eventService;
    private final ResponseService responseService;

    @PostMapping("/events")
    @ApiOperation(value="이벤트 생성", notes = "이벤트 이름, 내용, 기간을 받아 이벤트를 저장합니다.")
    public BaseResult createEvent(@RequestBody EventCreateReq eventCreateReq) {
        try {
            eventService.createEvent(eventCreateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/events/doing")
    @ApiOperation(value="진행중인 이벤트 가져오기", notes = "진행중인 이벤트들을 가져옵니다.")
    public BaseResult listAllDoingEvents() {
        try {
            return responseService
                    .listResult(eventService.listAllDoingEvents());
        }catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }

    @GetMapping("/events/done")
    @ApiOperation(value="완료된 이벤트 가져오기",notes = "종료된 이벤트들을 가져옵니다.")
    public BaseResult listAllDoneEvents() {
        try {
            return responseService
                    .listResult(eventService.listAllDoneEvents());
        }catch (Exception e) {
            return responseService.failResult(
                    e.getMessage()
            );
        }

    }

    @GetMapping("/events/{id}")
    @ApiOperation(value="특정 이벤트 가져오기", notes = "Pk에 해당하는 이벤트 하나를 받아옵니다.")
    public BaseResult getEventsDetail(@PathVariable Long id) {
        try {
            return responseService.singleResult(
                    eventService.getSingleEvent(id).toDto()
            );
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }


    @PutMapping("/events/{id}")
    @ApiOperation(value="이벤트 업데이트", notes = "특정 이벤트글의 내용을 업데이트 합니다.")
    public BaseResult updateEvent(@RequestBody EventUpdateReq eventUpdateReq) {
        try {
            eventService.updateEvent(eventUpdateReq);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }

    }

    @DeleteMapping("/events/{id}")
    @ApiOperation(value="이벤트 삭제")
    public BaseResult deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteEvent(id);
            return responseService.successResult();
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }

    }

}
