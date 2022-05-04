package com.EmperorPenguin.SangmyungBank.api.home;

import com.EmperorPenguin.SangmyungBank.api.users.login.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("")
@RestController
public class HomeContoller {

    private final SessionService sessionService;

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(HttpServletRequest request){
        sessionService.expire(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}