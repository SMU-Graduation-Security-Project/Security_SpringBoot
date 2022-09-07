package com.EmperorPenguin.SangmyungBank.loanlist.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.exception.*;
import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListCreateReq;
import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListInquiryRes;
import com.EmperorPenguin.SangmyungBank.cardlist.dto.CardListUpdateReq;
import com.EmperorPenguin.SangmyungBank.cardlist.entity.CardList;
import com.EmperorPenguin.SangmyungBank.cardlist.repository.CardListRepository;
import com.EmperorPenguin.SangmyungBank.loanlist.dto.LoanListCreateReq;
import com.EmperorPenguin.SangmyungBank.loanlist.dto.LoanListInquiryRes;
import com.EmperorPenguin.SangmyungBank.loanlist.dto.LoanListUpdateReq;
import com.EmperorPenguin.SangmyungBank.loanlist.entity.LoanList;
import com.EmperorPenguin.SangmyungBank.loanlist.repository.LoanListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanListService {

    private final LoanListRepository loanListRepository;

    @Transactional
    public void createLoanList(LoanListCreateReq loanListCreateReq) {
        if(loanListRepository.findByTitle(loanListCreateReq.getTitle()).isPresent()){
            throw new BaseException(ExceptionMessages.ERROR_LOANLIST_EXIST);
        }
        try{
            loanListRepository.save(loanListCreateReq.toEntity());
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("대출목록 생성에 실패했습니다.");
        }
    }

    @Transactional
    public List<LoanListInquiryRes> getAllLoanLists() {
        return loanListRepository.findAll()
                .stream()
                .map(LoanList::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public LoanList getSingleLoanList(Long id) {
        if(!loanListRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_LOANLIST_NOT_EXIST);
        }
        return loanListRepository
                .findById(id)
                .orElseThrow(() -> new BaseException(ExceptionMessages.ERROR_UNDEFINED));
    }

    @Transactional
    public void updateLoanList(LoanListUpdateReq loanListUpdateReq) {
        if(!loanListRepository.existsById(loanListUpdateReq.getId())){
            throw new BaseException(ExceptionMessages.ERROR_LOANLIST_NOT_EXIST);
        }
        try {
            loanListRepository.updateLoanList(loanListUpdateReq.getId(),loanListUpdateReq.getTitle(),loanListUpdateReq.getInterestRate(),loanListUpdateReq.getInterestType());
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("대출목록 업데이트에 실패했습니다.");
        }
    }

    @Transactional
    public void deleteLoanList(Long id) {
        if(!loanListRepository.existsById(id)){
            throw new BaseException(ExceptionMessages.ERROR_LOANLIST_NOT_EXIST);
        }
        try{
            loanListRepository.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new BaseException("대출목록 삭제에 실패했습니다.");
        }
    }
}
