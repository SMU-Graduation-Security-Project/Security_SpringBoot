package com.EmperorPenguin.SangmyungBank.api.user;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.login.domain.loginForm.LoginForm;
import com.EmperorPenguin.SangmyungBank.api.users.login.service.UserLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
public class loginUserTest {
    @Autowired
    UserLoginService userloginService;

    @Test
    void 사용자로그인() {
        // given
        String loginId = "test1";
        String rawPassword ="test1";
        LoginForm loginForm = LoginForm.builder()
                .loginId(loginId)
                .rawPassword(rawPassword)
                .build();

        // when
        User loginUser = userloginService.authenticate(loginForm);
        // then
        assertThat(loginUser.getLoginId()).isEqualTo(loginId);
        assertThat(loginUser.getPassword()).isEqualTo(rawPassword);
    }
}
