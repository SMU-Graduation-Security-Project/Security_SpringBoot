package com.EmperorPenguin.SangmyungBank.api.user;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.UserRepository;
import com.EmperorPenguin.SangmyungBank.api.users.add.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
public class addUserTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void 회원가입() {
        // given
        String loginId = "SpringTest1";
        String password = "12345";
        String name = "Spring";
        String email = "SpringTest@email.com";
        char sex = 'M';
        int age = 13;
        String phoneNumber = "010-1111-1111";
        // when
        userService.register(loginId,password,name,email,age,sex,phoneNumber);
        // then
        User findUser = userService.findOne(loginId).get();
        assertThat(loginId).isEqualTo(findUser.getLoginId());
    }
}
