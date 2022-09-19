package com.EmperorPenguin.SangmyungBank.event.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.event.dto.EventCreateReq;
import com.EmperorPenguin.SangmyungBank.event.dto.EventUpdateReq;
import com.EmperorPenguin.SangmyungBank.event.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(tags="11. 이벤트")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/guest")
public class EventController {

    private final EventService eventService;
    private final ResponseService responseService;

    @PostMapping("/events")
    @ApiOperation(value="1. 이벤트 생성", notes = "이벤트 이름, 내용, 기간을 받아 이벤트를 저장합니다.")
    public BaseResult createEvent(
            @ApiParam(value = "이벤트 객체", required = true)
            @RequestBody EventCreateReq eventCreateReq
    ) {
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
    @ApiOperation(value="2. 진행중인 이벤트 가져오기", notes = "진행중인 이벤트들을 가져옵니다.")
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
    @ApiOperation(value="3. 완료된 이벤트 가져오기",notes = "종료된 이벤트들을 가져옵니다.")
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
    @ApiOperation(value="4.특정 이벤트 가져오기", notes = "Pk에 해당하는 이벤트 하나를 받아옵니다.")
    public BaseResult getEventsDetail(
            @ApiParam(value = "이벤트 객체 ID", required = true)
            @PathVariable Long id
    ) {
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
    @ApiOperation(value="5. 이벤트 업데이트", notes = "특정 이벤트글의 내용을 업데이트 합니다.")
    public BaseResult updateEvent(
            @ApiParam(value = "변경된 이벤트 객체", required = true)
            @RequestBody EventUpdateReq eventUpdateReq
    ) {
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
    @ApiOperation(value="6. 이벤트 삭제")
    public BaseResult deleteEvent(
            @ApiParam(value = "이벤트 객체 ID", required = true)
            @PathVariable Long id
    ) {
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
