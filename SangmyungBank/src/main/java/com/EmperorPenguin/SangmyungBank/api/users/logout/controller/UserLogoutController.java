package com.EmperorPenguin.SangmyungBank.api.users.logout.controller;

import com.EmperorPenguin.SangmyungBank.api.users.login.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@RequestMapping("")
@RestController
public class UserLogoutController {

    private final SessionService sessionService;

    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest request) {
        sessionService.expire(request);
        return "redirect:/";
    }
}
