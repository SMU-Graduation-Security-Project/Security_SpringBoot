package com.SecurityGraduations.EmperorPenguin.service;

import com.SecurityGraduations.EmperorPenguin.domain.User;
import com.SecurityGraduations.EmperorPenguin.exception.IdNoExistedException;
import com.SecurityGraduations.EmperorPenguin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

    // Id를 통해 DB에 있는지 검색을 하고 있다면 존재한다는 error를 발생
    // 아니라면 등록과정을 진행한다.
    private void ValidateDuplicateMember(String Id) {
        userRepository.findById(Id)
                .ifPresent(m -> {
                    throw new IdNoExistedException(Id);
                });
    }

    /*
        회원 등록을 위한 작업
     */
    public User register(String id, String email, String password)
    {
        ValidateDuplicateMember(id);
        String encodedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .id(id)
                .email(email)
                .password(encodedPassword)
                .build();
        return userRepository.save(user);
    }

    /*
        회원 로그인을 위한 작업으로 ID, password를 제공하면
        password가 맞는지 확인 후 user 데이터를 제공
     */
    public User authenticate(String Id, String password)
    {
        User user  = userRepository.findById(Id).orElse(null);
        if(user == null) {
            return null;
        }
        else if(!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }
        return user;
    }


    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(String userId) {
        return userRepository.findById(userId);
    }

}
