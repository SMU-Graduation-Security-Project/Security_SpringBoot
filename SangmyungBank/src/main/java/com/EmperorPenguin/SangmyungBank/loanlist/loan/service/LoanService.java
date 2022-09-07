package com.EmperorPenguin.SangmyungBank.loanlist.loan.service;

import com.EmperorPenguin.SangmyungBank.account.service.AccountService;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.CardException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.EventException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.LoanException;
import com.EmperorPenguin.SangmyungBank.card.dto.CardCreateReq;
import com.EmperorPenguin.SangmyungBank.card.dto.CardRequestRes;
import com.EmperorPenguin.SangmyungBank.card.entity.Card;
import com.EmperorPenguin.SangmyungBank.card.repository.CardRepository;
import com.EmperorPenguin.SangmyungBank.loanlist.loan.dto.LoanCreateReq;
import com.EmperorPenguin.SangmyungBank.loanlist.loan.dto.LoanRequestRes;
import com.EmperorPenguin.SangmyungBank.loanlist.loan.entity.Loan;
import com.EmperorPenguin.SangmyungBank.loanlist.loan.repository.LoanRepository;
import com.EmperorPenguin.SangmyungBank.loanlist.service.LoanListService;
import com.EmperorPenguin.SangmyungBank.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberService memberService;
    private final AccountService accountService;
    private final LoanListService loanListService;

    @Transactional
    public void createLoan(LoanCreateReq loanCreateReq) {

        String loginId = loanCreateReq.getLoginId();
        Long accountNumber = loanCreateReq.getAccountNumber();
        Long loanList = loanCreateReq.getLoanList();
        Long amount = loanCreateReq.getAmount();

        if (amount > 5000000) {
            throw new LoanException(ExceptionMessages.ERROR_LOAN_AMOUNT_EXCESS);
        }
        try {
            loanRepository.save(loanCreateReq.toEntity(
                    memberService.getMember(loginId),
                    accountService.getAccount(accountNumber),
                    loanListService.getSingleLoanList(loanList))
            );
            loanRepository.updateBalance(amount, accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CardException("대출에 실패했습니다.");
        }
    }

    @Transactional
    public List<LoanRequestRes> loanList(String loginId) {
        return loanRepository
                .findAllByMemberId(memberService.getMember(loginId))
                .stream()
                .map(Loan::toDto)
                .collect(Collectors.toList());
    }



}
