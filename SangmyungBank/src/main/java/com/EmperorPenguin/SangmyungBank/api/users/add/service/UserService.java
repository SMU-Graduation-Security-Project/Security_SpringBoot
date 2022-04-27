package com.EmperorPenguin.SangmyungBank.api.users.add.service;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.Role;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.transaction.Transactional;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    // Id를 통해 DB에 있는지 검색을 하고 있다면 존재한다는 error를 발생
    // 아니라면 등록과정을 진행한다.
    public boolean ValidateDuplicateUser(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElse(null);
        if(user == null)
            return true;
        else
            return false;
    }

    /*
        회원 등록을 위한 작업
     */
    public User register(User savedUser)
    {
        if(ValidateDuplicateUser(savedUser.getLoginId()))
        {
            //        String encodedPassword = passwordEncoder.encode(password);
            return userRepository.save(savedUser);
        }
        else
            return null;
    }

    public Optional<User> findOne(String userId) {
        return userRepository.findByLoginId(userId);
    }
}
