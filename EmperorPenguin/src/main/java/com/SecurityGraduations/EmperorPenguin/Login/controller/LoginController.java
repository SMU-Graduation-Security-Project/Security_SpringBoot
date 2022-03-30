package com.SecurityGraduations.EmperorPenguin.Login.controller;

import com.SecurityGraduations.EmperorPenguin.Login.domain.LoginForm;
import com.SecurityGraduations.EmperorPenguin.Login.domain.User;
import com.SecurityGraduations.EmperorPenguin.Login.service.UserService;
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
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "users/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "users/loginForm";
        }
        User loginUser = userService.authenticate(loginForm.getLoginID(), loginForm.getPassword());

        if(loginUser == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "users/loginForm";
        }
        return "loginSuccess";
    }

}
