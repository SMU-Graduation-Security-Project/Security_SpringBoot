package com.EmperorPenguin.SangmyungBank.api.users.add.controller;


import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.add.service.UserAddService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserAddController {

    private final UserAddService userService;

    @PostMapping(path="/add")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user)
    {
        user.setCreatedDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());
        User savedUser = userService.register(user);
        if (savedUser != null)
            return ResponseEntity.ok(HttpStatus.OK);
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

}
