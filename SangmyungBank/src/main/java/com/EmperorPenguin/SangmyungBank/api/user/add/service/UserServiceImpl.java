package com.EmperorPenguin.SangmyungBank.api.user.add.service;

import com.EmperorPenguin.SangmyungBank.api.user.add.domain.User.Role;
import com.EmperorPenguin.SangmyungBank.api.user.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.user.add.domain.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }


    // Id를 통해 DB에 있는지 검색을 하고 있다면 존재한다는 error를 발생
    // 아니라면 등록과정을 진행한다.
    @Override
    public boolean ValidateDuplicateUser(String loginid) {
        User user = userRepository.findByLoginId(loginid)
                .orElse(null);
        if(user == null)
            return false;
        else
            return true;
    }

    /*
        회원 등록을 위한 작업
     */
    @Override
    public User register(String loginId, String password, String name, String email, int age, char sex, String phoneNumber)
    {
//        String encodedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .age(age)
                .sex(sex)
                .phoneNumber(phoneNumber)
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    /*
        회원 로그인을 위한 작업으로 ID, password를 제공하면
        password가 맞는지 확인 후 user 데이터를 제공
     */
    @Override
    public User authenticate(User loginUser) {
        User findUser  = userRepository.findByLoginId(loginUser.getLoginId())
                .orElse(null);
        if(findUser == null)
        {
            return null;
        }
//        else if(!passwordEncoder.matches(User.getPassword(), user.getPassword()))
//        {
//            return null;
//        }
        else if(!findUser.getPassword().equals(loginUser.getPassword())) {
            return null;
        }
        return findUser;
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findOne(String userId) {
        return userRepository.findByLoginId(userId);
    }
}
