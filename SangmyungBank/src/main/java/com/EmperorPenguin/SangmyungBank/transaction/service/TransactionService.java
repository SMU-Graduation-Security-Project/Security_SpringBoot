package com.EmperorPenguin.SangmyungBank.transaction.service;

import com.EmperorPenguin.SangmyungBank.account.repository.AccountRepository;
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

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public List<TransactionInquiryRes> showTransactions(TransactionInquiryReq transactionInquiryReq){
        //  사용자의 계좌가 없다면 예외를 발생.
        if(!accountRepository.existsAccountByAccountNumber(transactionInquiryReq.getAccountNumber())){
            throw new TransactionException(ExceptionMessages.ERROR_ACCOUNT_NOT_FOUND);
        }
        return transactionRepository.getAllBySendAccount(transactionInquiryReq.getAccountNumber()
                , transactionInquiryReq.getStartDate()
                , transactionInquiryReq.getEndDate())
                    .stream()
                    .map(Transaction::toDto)
                    .collect(Collectors.toList());
    }
}
