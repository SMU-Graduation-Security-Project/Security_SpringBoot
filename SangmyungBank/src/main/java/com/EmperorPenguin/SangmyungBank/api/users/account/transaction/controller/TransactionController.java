package com.EmperorPenguin.SangmyungBank.api.users.account.transaction.controller;

import com.EmperorPenguin.SangmyungBank.api.users.account.transaction.domain.transactionForm.TransactionForm;
import com.EmperorPenguin.SangmyungBank.api.users.account.transaction.service.AccountTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users/accounts")
@RestController
public class TransactionController {

    private final AccountTransactionService accountTransactionService;

    @PostMapping(path="/transaction")
    public ResponseEntity<HttpStatus> sendBalance(@RequestBody TransactionForm transactionForm){
       boolean check = accountTransactionService.transaction(transactionForm);
       if(!check)
           return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
       else
           return ResponseEntity.ok(HttpStatus.OK);
    }
}

