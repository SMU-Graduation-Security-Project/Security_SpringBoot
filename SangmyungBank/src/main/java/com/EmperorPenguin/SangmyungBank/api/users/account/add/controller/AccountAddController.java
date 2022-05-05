package com.EmperorPenguin.SangmyungBank.api.users.account.add.controller;

import com.EmperorPenguin.SangmyungBank.api.users.account.add.domain.account.Account;
import com.EmperorPenguin.SangmyungBank.api.users.account.add.domain.accountForm.AccountForm;
import com.EmperorPenguin.SangmyungBank.api.users.account.add.service.AccountAddService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class AccountAddController {

    private final AccountAddService accountAddService;

    @PostMapping(path="/accounts/add")
    public ResponseEntity<HttpStatus> addAccount(@RequestBody AccountForm accountForm){
        Account resultAccount = accountAddService.register(accountForm);
        if(resultAccount == null)
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        else
            return ResponseEntity.ok(HttpStatus.OK);
    }

}
