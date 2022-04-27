package com.EmperorPenguin.SangmyungBank.api.users.add.web;


import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.UserRepository;
import com.EmperorPenguin.SangmyungBank.api.users.add.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping(path="/users/add")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user)
    {
        User savedUser = userService.register(user);
        if (savedUser != null)
            return ResponseEntity.ok(HttpStatus.OK);
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

}
