package com.EmperorPenguin.SangmyungBank.api.user;

import com.EmperorPenguin.SangmyungBank.api.user.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.user.add.domain.repository.UserRepository;
import com.EmperorPenguin.SangmyungBank.api.user.add.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class addUserTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Test
    void 회원가입() {
        // given
        String id = "SpringTest1";
        String rawPassword = "12345";
        String name = "Spring";
        String email = "SpringTest@email.com";
        char sex = 'M';
        int age = 13;
        String phoneNumber = "010-1111-1111";
        // when
        userService.register(id,rawPassword,name,email,age,sex,phoneNumber);
        // then
        User findUser = userService.findOne(id).get();
        assertThat(id).isEqualTo(findUser.getLoginId());
    }
}
