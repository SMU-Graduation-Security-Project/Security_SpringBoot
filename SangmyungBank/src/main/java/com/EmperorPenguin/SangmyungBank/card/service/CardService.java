package com.EmperorPenguin.SangmyungBank.card.service;


import com.EmperorPenguin.SangmyungBank.account.service.AccountService;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.CardException;
import com.EmperorPenguin.SangmyungBank.card.dto.CardCreateReq;
import com.EmperorPenguin.SangmyungBank.card.dto.CardRequestRes;
import com.EmperorPenguin.SangmyungBank.card.entity.Card;
import com.EmperorPenguin.SangmyungBank.card.repository.CardRepository;
import com.EmperorPenguin.SangmyungBank.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final MemberService memberService;
    private final AccountService accountService;

    @Transactional
    public void createCard(CardCreateReq cardCreateReq) {

        String loginId = cardCreateReq.getLoginId();
        Long accountNumber = cardCreateReq.getAccountNumber();

        try {
            cardRepository.save(cardCreateReq.toEntity(
                    memberService.getMember(loginId),
                    accountService.getAccount(accountNumber))
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new CardException("카드 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<CardRequestRes> cardList(String loginId) {
        // 정확한 사용자를 넘겨줬는지 확인
        memberService.checkEmptyMember(loginId);

        return cardRepository
                .findAllByMemberId(memberService.getMember(loginId))
                .stream()
                .map(Card::toDto)
                .collect(Collectors.toList());
    }


}
