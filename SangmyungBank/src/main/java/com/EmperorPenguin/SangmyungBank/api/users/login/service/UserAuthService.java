package com.EmperorPenguin.SangmyungBank.api.users.login.service;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.login.domain.loginForm.LoginForm;
import com.EmperorPenguin.SangmyungBank.api.users.login.domain.repository.UserAuthRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserAuthService {

    private final UserAuthRepository userAuthRepository;

    public UserAuthService(UserAuthRepository userAuthRepository ) {
        this.userAuthRepository = userAuthRepository;
    }

    public boolean authenticate(LoginForm loginForm)
    {
        User DbUser = userAuthRepository.findById(loginForm.getLoginId()).get();
        if(loginForm.getRawPassword().equals(DbUser.getPassword()))
            return true;
        else
            return false;
    }
}
