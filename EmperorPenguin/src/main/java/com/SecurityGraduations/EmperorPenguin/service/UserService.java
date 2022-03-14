package com.SecurityGraduations.EmperorPenguin.service;

import com.SecurityGraduations.EmperorPenguin.domain.User;
import com.SecurityGraduations.EmperorPenguin.exception.IdExistedException;
import com.SecurityGraduations.EmperorPenguin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String Id, String Email, String Password)
    {
        // Id를 통해 DB에 있는지 검색을 하고 있다면 존재한다는 error를 발생
        // 아니라면 등록과정을 진행한다.
        Optional<User> existed = userRepository.findByID(Id);
        if (existed.isPresent()){
            throw new IdExistedException(Id);
        }
        String encodedPassword = passwordEncoder.encode(Password);
        User user = User.builder()
                .ID(Id)
                .Email(Email)
                .Password(encodedPassword)
                .salt()
    }

}
