package com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.login.service;


import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.login.domain.loginForm.LoginForm;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.login.domain.repository.UserLoginRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserLoginService {

    private final UserLoginRepository userAuthRepository;

    public UserLoginService(UserLoginRepository userAuthRepository ) {
        this.userAuthRepository = userAuthRepository;
    }

    public User authenticate(LoginForm loginForm)
    {
        User DbUser = userAuthRepository.findById(loginForm.getLoginId()).get();
        if(loginForm.getRawPassword().equals(DbUser.getPassword()))
            return DbUser;
        else
            return null;
    }
}
