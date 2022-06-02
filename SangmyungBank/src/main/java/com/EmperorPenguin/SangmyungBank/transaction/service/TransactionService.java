package com.EmperorPenguin.SangmyungBank.transaction.service;

import com.EmperorPenguin.SangmyungBank.account.service.AccountService;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.TransactionException;
import com.EmperorPenguin.SangmyungBank.transaction.dto.TransactionInquiryReq;
import com.EmperorPenguin.SangmyungBank.transaction.dto.TransactionInquiryRes;
import com.EmperorPenguin.SangmyungBank.transaction.entity.Transaction;
import com.EmperorPenguin.SangmyungBank.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    @Transactional
    public List<TransactionInquiryRes> showTransactions(TransactionInquiryReq transactionInquiryReq){
        //  사용자의 계좌가 없다면 예외를 발생.
        accountService.checkAccount(transactionInquiryReq.getAccountNumber());

        return transactionRepository.getAllBySendAccount(transactionInquiryReq.getAccountNumber()
                , transactionInquiryReq.getStartDate()
                , transactionInquiryReq.getEndDate())
                    .stream()
                    .map(Transaction::toDto)
                    .collect(Collectors.toList());
    }

    @Transactional
    public void saveData(Transaction transaction){
        try{
            transactionRepository.save(transaction);
        }catch (Exception e) {
            throw new TransactionException(ExceptionMessages.ERROR_UNDEFINED);
        }
    }
}
