package com.EmperorPenguin.SangmyungBank.api.users.register.service;

import com.EmperorPenguin.SangmyungBank.api.users.register.domain.User.Role;
import com.EmperorPenguin.SangmyungBank.api.users.register.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.register.domain.repository.UserRegisterRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.transaction.Transactional;

@Transactional
@Service
public class UserRegisterService {

    private final UserRegisterRepository userRegisterRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserRegisterService(UserRegisterRepository userRegisterRepository) {
        this.userRegisterRepository = userRegisterRepository;
    }

    // Id를 통해 DB에 있는지 검색을 하고 있다면 존재한다는 error를 발생
    // 아니라면 등록과정을 진행한다.
    public boolean ValidateDuplicateUserId(String saveUser) {
        User user = userRegisterRepository.findByLoginId(saveUser)
                .orElse(null);
        return user == null;
    }

    // 사용자 이메일에 대한 검증을 진행
    public boolean ValidateDuplicateEmail(String email){
        return userRegisterRepository.RepeatEmailCheck(email);
    }

    // 사용자 전화번호에 대한 검증을 진행
    public boolean ValidateDuplicatePhoneNumber(String PhoneNumber) {
        return userRegisterRepository.RepeatPhoneNumber(PhoneNumber);
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
            return userRegisterRepository.save(savedUser);
        }
        else
            return null;
    }

    public Optional<User> findOne(String loginId) {
        return userRegisterRepository.findByLoginId(loginId);
    }
}
