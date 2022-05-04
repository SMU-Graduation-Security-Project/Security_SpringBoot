package com.EmperorPenguin.SangmyungBank.api.users.login.controller;

import com.EmperorPenguin.SangmyungBank.api.users.login.domain.frontForm.FrontForm;
import com.EmperorPenguin.SangmyungBank.api.users.login.domain.loginForm.LoginForm;
import com.EmperorPenguin.SangmyungBank.api.users.login.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EmperorPenguin.SangmyungBank.api.users.login.service.SessionService;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserLoginController {

    private final UserLoginService userLoginService;
    private final SessionService sessionService;


    @RequestMapping (path="/login",method={RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<FrontForm> authUser(@RequestBody LoginForm loginForm,HttpServletResponse response)
    {
        User auth = userLoginService.authenticate(loginForm);
        if (auth == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        else {
            sessionService.CreateSession(auth, response);
            FrontForm frontForm = FrontForm.builder()
                    .loginId(auth.getLoginId())
                    .userName(auth.getName())
                    .build();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(frontForm);
        }

    }







}
