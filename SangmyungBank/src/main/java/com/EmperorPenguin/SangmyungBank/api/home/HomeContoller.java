package com.EmperorPenguin.SangmyungBank.api.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContoller {

    @GetMapping("/")
    public String home()
    {
        return "home";
    }
}
