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
    public boolean ValidateDuplicateUserId(String saveUser) {
        User user = userAddRepository.findByLoginId(saveUser)
                .orElse(null);
        return user == null;
    }

    // 사용자 이메일에 대한 검증을 진행
    public boolean ValidateDuplicateEmail(String email){
        return userAddRepository.RepeatEmailCheck(email);
    }

    // 사용자 전화번호에 대한 검증을 진행
    public boolean ValidateDuplicatePhoneNumber(String PhoneNumber) {
        return userAddRepository.RepeatPhoneNumber(PhoneNumber);
    }

    /*
        회원 등록을 위한 작업
     */
    public User register(User savedUser)
    {
        // 사용자 아이디, 이메일, 전화번호에 대한 검증이 마무리 되면 회원가입을 진행.
        if(ValidateDuplicateUserId(savedUser.getLoginId()) & ValidateDuplicateEmail(savedUser.getEmail()) & ValidateDuplicatePhoneNumber(savedUser.getPhoneNumber()))
        {
            //        String encodedPassword = passwordEncoder.encode(password);
            savedUser.setRole(Role.USER);
            return userAddRepository.save(savedUser);
        }
        else
            return null;
    }

    public Optional<User> findOne(String loginId) {
        return userAddRepository.findByLoginId(loginId);
    }
}
