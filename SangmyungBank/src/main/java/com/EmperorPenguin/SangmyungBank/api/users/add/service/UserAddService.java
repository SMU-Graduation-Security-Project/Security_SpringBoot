package com.EmperorPenguin.SangmyungBank.api.users.add.service;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.Role;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.UserAddRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.transaction.Transactional;

@Transactional
@Service
public class UserAddService {

    private final UserAddRepository userAddRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserAddService(UserAddRepository userAddRepository ) {
        this.userAddRepository = userAddRepository;
    }

    // Id를 통해 DB에 있는지 검색을 하고 있다면 존재한다는 error를 발생
    // 아니라면 등록과정을 진행한다.
    public boolean ValidateDuplicateUser(User saveUser) {
        User user = userAddRepository.findByLoginId(saveUser.getLoginId())
                .orElse(null);

        if(user == null & userAddRepository.RepeatEmailCheck(saveUser.getEmail())
                & userAddRepository.RepeatPhoneNumber(saveUser.getPhoneNumber()))
            return true;
        else
            return false;
    }

    /*
        회원 등록을 위한 작업
     */
    public User register(User savedUser)
    {
        if(ValidateDuplicateUser(savedUser))
        {
            //        String encodedPassword = passwordEncoder.encode(password);
            savedUser.setRole(Role.USER);
            return userAddRepository.save(savedUser);
        }
        else
            return null;
    }

    public Optional<User> findOne(String userId) {
        return userAddRepository.findByLoginId(userId);
    }
}
