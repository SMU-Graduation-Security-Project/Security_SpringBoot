package com.EmperorPenguin.SangmyungBank.api.user;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.Role;
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
        User savedUser = User.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .email(email)
                .age(age)
                .sex(sex)
                .phoneNumber(phoneNumber)
                .role(Role.USER)
                .build();

        userService.register(savedUser);
        // then
        User findUser = userService.findOne(loginId).get();
        assertThat(loginId).isEqualTo(findUser.getLoginId());
    }
}
