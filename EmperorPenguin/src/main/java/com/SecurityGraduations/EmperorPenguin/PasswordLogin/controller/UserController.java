package com.SecurityGraduations.EmperorPenguin.PasswordLogin.controller;

import com.SecurityGraduations.EmperorPenguin.PasswordLogin.domain.User;
import com.SecurityGraduations.EmperorPenguin.PasswordLogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user) {
        return "users/addUserForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/addUserForm";
        }
        if (userService.ValidateDuplicateUser(user.getId()))
        {
            result.reject("loginFail", "중복된 아이디 입니다. 다른 아이디를 입력해주세요");
            return "users/addUserForm";
        }
        userService.register(user.getId(),user.getPassword(), user.getName(), user.getEmail());
        return "redirect:/";
    }

}
