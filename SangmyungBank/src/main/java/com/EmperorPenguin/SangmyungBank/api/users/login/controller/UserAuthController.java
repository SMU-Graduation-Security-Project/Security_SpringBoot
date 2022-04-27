package com.EmperorPenguin.SangmyungBank.api.users.login.controller;

import com.EmperorPenguin.SangmyungBank.api.users.login.domain.loginForm.LoginForm;
import com.EmperorPenguin.SangmyungBank.api.users.login.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserAuthController {

    private final UserAuthService userAuthService;

    @GetMapping(path="/login")
    public ResponseEntity<HttpStatus> authUser(@RequestBody LoginForm loginForm)
    {
        boolean auth = userAuthService.authenticate(loginForm);
        if (auth == true)
            return ResponseEntity.ok(HttpStatus.OK);
        else
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }
}
