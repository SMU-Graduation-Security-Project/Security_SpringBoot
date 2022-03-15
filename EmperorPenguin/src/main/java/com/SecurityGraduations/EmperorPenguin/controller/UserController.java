package com.SecurityGraduations.EmperorPenguin.controller;

import com.SecurityGraduations.EmperorPenguin.domain.User;
import com.SecurityGraduations.EmperorPenguin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    // 의존관계 주입.
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //조회할 때 사용.
    @GetMapping("/user/new")
    public String createForm(){
        return "user/createUserForm";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<User> members = userService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
