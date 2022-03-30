package com.SecurityGraduations.EmperorPenguin.Login.service;

import com.SecurityGraduations.EmperorPenguin.Login.domain.LoginForm;
import com.SecurityGraduations.EmperorPenguin.Login.domain.User;
import com.SecurityGraduations.EmperorPenguin.Login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Id를 통해 DB에 있는지 검색을 하고 있다면 존재한다는 error를 발생
    // 아니라면 등록과정을 진행한다.
    public boolean ValidateDuplicateUser(String Id) {
        User user = userRepository.findById(Id)
                .orElse(null);
        if(user == null)
            return false;
        else
            return true;
    }

    /*
        회원 등록을 위한 작업
     */
    public User register(String id, String password, String name, String email)
    {
        String encodedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .id(id)
                .password(encodedPassword)
                .name(name)
                .email(email)
                .build();
        return userRepository.save(user);
    }

    /*
        회원 로그인을 위한 작업으로 ID, password를 제공하면
        password가 맞는지 확인 후 user 데이터를 제공
     */
    public User authenticate(LoginForm loginForm)
    {
        User user  = userRepository.findById(loginForm.getLoginID()).orElse(null);
        if(user == null) {
            return null;
        }
        else if(!passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
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