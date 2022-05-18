package com.EmperorPenguin.SangmyungBank.account.service;

import com.EmperorPenguin.SangmyungBank.account.dto.AccountCreateReq;
import com.EmperorPenguin.SangmyungBank.account.dto.AccountInquiryRes;
import com.EmperorPenguin.SangmyungBank.account.dto.TransactionReq;
import com.EmperorPenguin.SangmyungBank.account.entity.Account;
import com.EmperorPenguin.SangmyungBank.account.repository.AccountRepository;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.AccountException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.user.entity.User;
import com.EmperorPenguin.SangmyungBank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createAccount(AccountCreateReq accountCreateReq) {
        String loginId = accountCreateReq.getLoginId();
        String password = accountCreateReq.getAccountPassword();

        checkPassword(password);
        // 전달 받은 아이디를 통해 사용자가 있는지 확인 없다면 사용자가 없다는 예외를 발생.
        if(userRepository.findByLoginId(loginId).isEmpty()){
            throw new AccountException(ExceptionMessages.ERROR_USER_NOT_FOUND);
        }
        try{
            accountRepository.save(accountCreateReq.toEntity(
                    userRepository.findByLoginId(loginId).get(),
                    passwordEncoder.encode(password),
                    0L));
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new AccountException("계좌생성에 실패했습니다.");
        }
    }

    @Transactional
    public void transaction(TransactionReq transactionReq)
    {
        String loginId = transactionReq.getLoginId();
        Long sendAccount = transactionReq.getSendAccountNumber();
        String password = transactionReq.getAccountPassword();

        Account myAccount = accountRepository
            .findAccountByAccountNumber(transactionReq.getMyAccountNumber())
            .orElseThrow(() -> new AccountException(ExceptionMessages.ERROR_ACCOUNT_NOT_FOUND));

        // 사용자의 아이디로부터 자신의 계좌가 맞는지 확인.
        checkAccount(myAccount, userRepository.findByLoginId(loginId).get());

        // 보내는 계좌 번호가 존재하는지 확인.
        if(!accountRepository.existsAccountByAccountNumber(sendAccount)){
            throw new AccountException(ExceptionMessages.ERROR_ACCOUNT_NOT_FOUND);
        }
        // 사용자의 계좌 비밀번호가 맞는지 확인.
        if(!passwordEncoder.matches(password, myAccount.getAccountPassword())) {
            throw new AccountException(ExceptionMessages.ERROR_ACCOUNT_PASSWORD_NOT_MATCH);
        }
        // 사용자의 계좌에 충분한 잔액이 있는지 확인.
        if(myAccount.getBalance() < transactionReq.getBalance()) {
            throw new AccountException(ExceptionMessages.ERROR_ACCOUNT_BALANCE);
        }
        try {
            accountRepository.updateMyBalance(transactionReq.getBalance(),
                    myAccount.getAccountNumber());
            accountRepository.updateBalance(transactionReq.getBalance(),
                    transactionReq.getSendAccountNumber());
        }catch (Exception e){
            e.printStackTrace();
            throw new AccountException("계좌이체에 실패했습니다.");
        }
    }

    @Transactional
    public List<AccountInquiryRes> inquiry(String loginId) {
        // 정확한 사용자를 넘겨줬는지 확인
        if (userRepository.findByLoginId(loginId).isEmpty()) {
            throw new AccountException(ExceptionMessages.ERROR_USER_NOT_FOUND);
        }
        return accountRepository
                .findAllByUserId(userRepository.findByLoginId(loginId).get())
                .stream()
                .map(Account::toDto)
                .collect(Collectors.toList());
    }

    private void checkPassword(String password)
    {
        // 계좌 비밀번호는 숫자로 6자로 구성되어있다.
        Pattern passwordExpression = Pattern.compile("[0-9]{6}");
        if(!passwordExpression.matcher(password).matches()){
            throw new AccountException(ExceptionMessages.ERROR_ACCOUNT_PASSWORD_FORMAT);
        }
    }

    private void checkAccount(Account account, User user){
        if(!accountRepository.findAllByUserId(user).contains(account)){
            throw new AccountException("전달받은 계좌는 사용자의 계좌가 아닙니다.");
        }
    }
}
