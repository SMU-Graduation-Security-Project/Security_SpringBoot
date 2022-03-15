package com.SecurityGraduations.EmperorPenguin.service;

import com.SecurityGraduations.EmperorPenguin.domain.User;
import com.SecurityGraduations.EmperorPenguin.exception.IdExistedException;
import com.SecurityGraduations.EmperorPenguin.exception.PasswordWrongException;
import com.SecurityGraduations.EmperorPenguin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {

    UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Id를 통해 DB에 있는지 검색을 하고 있다면 존재한다는 error를 발생
    // 아니라면 등록과정을 진행한다.
    private void ValidateDuplicateMember(String Id) {
        userRepository.findByID(Id)
                .ifPresent(m -> {
                    throw new IdExistedException(Id);
                });
    }

    /*
        회원 등록을 위한 작업
     */
    public User register(String Id, String Email, String Password)
    {
        ValidateDuplicateMember(Id);
        String encodedPassword = passwordEncoder.encode(Password);
        User user = User.builder()
                .ID(Id)
                .Email(Email)
                .Password(encodedPassword)
                .build();
        return userRepository.save(user);
    }

    public User authenticate(String Id, String password)
    {
        User user  = userRepository.findByID(Id)
                .orElseThrow(()-> new IdExistedException(Id));
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordWrongException();
        }
        return user;
    }


    public List<User> findMembers() {
        /*
        시간을 찍기위했던 로직들
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
        */
        return userRepository.findAll();
    }

    public Optional<User> findOne(String userId) {
        return userRepository.findByID(userId);
    }

}
