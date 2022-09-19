package com.EmperorPenguin.SangmyungBank.winner.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.*;
import com.EmperorPenguin.SangmyungBank.event.service.EventService;
import com.EmperorPenguin.SangmyungBank.winner.dto.WinnerCreateReq;
import com.EmperorPenguin.SangmyungBank.winner.dto.WinnerRequestRes;
import com.EmperorPenguin.SangmyungBank.winner.dto.WinnerUpdateReq;
import com.EmperorPenguin.SangmyungBank.winner.entity.Winner;
import com.EmperorPenguin.SangmyungBank.winner.repository.WinnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WinnerService {

    private final WinnerRepository winnerRepository;
    private final EventService eventService;

    @Transactional
    public void createWinner(WinnerCreateReq winnerCreateReq) {

        Long eventId = winnerCreateReq.getEventId();

        if (winnerRepository.findByEventId(eventService.getSingleEvent(eventId)).isPresent()) {
            throw new BaseException(ExceptionMessages.ERROR_WINNER_EXIST);
        }
        try{
            winnerRepository.save(winnerCreateReq.toEntity(
                    eventService.getSingleEvent(eventId)
            ));
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("당첨자 리스트 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<WinnerRequestRes> getAllWinnerLists() {
        return winnerRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Winner::getCreateDate).reversed())
                .map(Winner::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public WinnerRequestRes getSingleWinner(Long id) {
        if(!winnerRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_WINNER_NOT_EXIST);
        }
        return winnerRepository
                .findById(id)
                .orElseThrow(() -> new BaseException(ExceptionMessages.ERROR_UNDEFINED)
                ).toDto();
    }

    @Transactional
    public void updateWinner(WinnerUpdateReq winnerUpdateReq) {
        if(!winnerRepository.existsById(winnerUpdateReq.getId())){
            throw new BaseException(ExceptionMessages.ERROR_WINNER_NOT_EXIST);
        }
        try {
            winnerRepository.updateWinner(winnerUpdateReq.getId(),winnerUpdateReq.getContent());
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("당첨자 리스트 업데이트에 실패했습니다.");
        }

    }

    @Transactional
    public void deleteWinner(Long id) {
        if(!winnerRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_WINNER_NOT_EXIST);
        }
        try{
            winnerRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("당첨자 리스트 삭제에 실패했습니다.");
        }
    }
}
