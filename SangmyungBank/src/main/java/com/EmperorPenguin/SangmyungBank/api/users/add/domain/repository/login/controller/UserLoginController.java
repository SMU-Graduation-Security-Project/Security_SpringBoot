package com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.login.controller;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.login.domain.loginForm.LoginForm;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.login.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.login.service.SessionService;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;

import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserLoginController {

    private final UserLoginService userLoginService;
    private final SessionService sessionService;


    @RequestMapping (path="/login",method={RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<User> authUser(@RequestBody LoginForm loginForm,HttpServletResponse response)
    {
        User auth = userLoginService.authenticate(loginForm);
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(auth);
        }
        else {
            sessionService.CreateSession(auth, response);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(auth);
        }

    }
//    @PostMapping("/logout")
//    public String logoutV2(HttpServletRequest request) {
//        sessionManager.expire(request);
//        return "redirect:/";
//    }






}
