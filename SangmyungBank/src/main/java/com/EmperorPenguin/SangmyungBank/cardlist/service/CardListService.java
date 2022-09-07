package com.EmperorPenguin.SangmyungBank.cardlist.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.BaseException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListCreateReq;
import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListInquiryRes;
import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListUpdateReq;
import com.EmperorPenguin.SangmyungBank.cardlist.entity.CardList;
import com.EmperorPenguin.SangmyungBank.cardlist.repository.CardListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardListService {

    private final CardListRepository cardListRepository;

    @Transactional
    public void createCardList(CardListCreateReq cardListCreateReq) {
        if(cardListRepository.findByTitle(cardListCreateReq.getTitle()).isPresent()){
            throw new BaseException(ExceptionMessages.ERROR_CARDLIST_EXIST);
        }
        try{
            cardListRepository.save(cardListCreateReq.toEntity());
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("카드목록 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<CardListInquiryRes> getAllCardLists() {
        return cardListRepository.findAll()
                .stream()
                .map(CardList::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CardList getSingleCardList(Long id) {
        if(!cardListRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_CARDLIST_NOT_EXIST);
        }
        return cardListRepository
                .findById(id)
                .orElseThrow(() -> new BaseException(ExceptionMessages.ERROR_UNDEFINED));
    }

    @Transactional
    public void updateCardList(CardListUpdateReq cardListUpdateReq) {
        if(!cardListRepository.existsById(cardListUpdateReq.getId())){
            throw new BaseException(ExceptionMessages.ERROR_CARDLIST_NOT_EXIST);
        }
        try {
            cardListRepository.updateCardList(cardListUpdateReq.getId(),cardListUpdateReq.getTitle(),cardListUpdateReq.getContent());
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("카드목록 업데이트에 실패했습니다.");
        }
    }

    @Transactional
    public void deleteCardList(Long id) {
        if(!cardListRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_CARDLIST_NOT_EXIST);
        }
        try{
            cardListRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("카드목록 삭제에 실패했습니다.");
        }
    }
}
