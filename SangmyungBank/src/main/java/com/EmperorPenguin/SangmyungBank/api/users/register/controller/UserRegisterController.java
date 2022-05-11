package com.EmperorPenguin.SangmyungBank.api.users.register.controller;


import com.EmperorPenguin.SangmyungBank.api.users.register.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.register.service.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    @PostMapping(path="/register")
    public ResponseEntity<HttpStatus> RegisterUser(@RequestBody User user)
    {
        user.setCreatedDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());
        User savedUser = userRegisterService.register(user);
        if (savedUser != null)
            return ResponseEntity.ok(HttpStatus.OK);
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

}
