package com.EmperorPenguin.SangmyungBank.user.service;

import com.EmperorPenguin.SangmyungBank.user.dto.UserReq;
import com.EmperorPenguin.SangmyungBank.user.entity.User;
import com.EmperorPenguin.SangmyungBank.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User Save(UserReq userRequestDto)
    {

    }
}
