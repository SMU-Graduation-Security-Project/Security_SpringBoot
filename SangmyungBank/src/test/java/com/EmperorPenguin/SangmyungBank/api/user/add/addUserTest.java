package com.EmperorPenguin.SangmyungBank.api.user.add;

import com.EmperorPenguin.SangmyungBank.api.users.register.domain.User.Role;
import com.EmperorPenguin.SangmyungBank.api.users.register.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.register.service.UserRegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
public class addUserTest {

    @Autowired
    UserRegisterService userRegisterService;

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

        userRegisterService.register(savedUser);
        // then
        User findUser = userRegisterService.findOne(loginId).get();
        assertThat(loginId).isEqualTo(findUser.getLoginId());
    }
}
