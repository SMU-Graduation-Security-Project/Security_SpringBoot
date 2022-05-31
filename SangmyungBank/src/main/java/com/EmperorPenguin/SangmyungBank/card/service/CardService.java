package com.EmperorPenguin.SangmyungBank.card.service;

import com.EmperorPenguin.SangmyungBank.account.dto.AccountInquiryRes;
import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.account.repository.AccountRepository;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.AccountException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.CardException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.CounselException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.card.dto.CardCreateReq;
import com.EmperorPenguin.SangmyungBank.card.dto.CardRequestRes;
import com.EmperorPenguin.SangmyungBank.card.entity.Card;
import com.EmperorPenguin.SangmyungBank.card.repository.CardRepository;
import com.EmperorPenguin.SangmyungBank.counsel.entity.Counsel;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void createCard(CardCreateReq cardCreateReq) {

        String loginId = cardCreateReq.getLoginId();
        Long accountNumber = cardCreateReq.getAccountNumber();

        try {
            cardRepository.save(cardCreateReq.toEntity(
                    memberRepository.findByLoginId(loginId).get(),
                    accountRepository.findAccountByAccountNumber(accountNumber).get())
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new CardException("카드 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<CardRequestRes> cardList(String loginId) {
        // 정확한 사용자를 넘겨줬는지 확인
        if (memberRepository.findByLoginId(loginId).isEmpty()) {
            throw new CardException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND);
        }
        return cardRepository
                .findAllByMemberId(memberRepository.findByLoginId(loginId).get())
                .stream()
                .map(Card::toDto)
                .collect(Collectors.toList());
    }

//    @Transactional
//    public void closeCard(Long id, String loginId) {
//        if(!cardRepository.existsById(id)){
//            throw new CardException(ExceptionMessages.ERROR_CARD_NOT_EXIST);
//        }
//        Card card = cardRepository.getById(id);
//        if(!card.getMemberId().getLoginId().equals(loginId)){
//            throw new CounselException(ExceptionMessages.ERROR_CARD_UNAUTHORIZED_ACCESS);
//        }
//        try{
//            cardRepository.deleteById(id);
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new CounselException("카드 해지에 실패했습니다.");
//        }
//    }
}
