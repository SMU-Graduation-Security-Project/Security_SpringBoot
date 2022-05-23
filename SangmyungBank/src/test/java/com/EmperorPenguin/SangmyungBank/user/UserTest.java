package com.EmperorPenguin.SangmyungBank.user;
import com.EmperorPenguin.SangmyungBank.user.dto.UserFindPasswordReq;
import com.EmperorPenguin.SangmyungBank.user.dto.UserLoginReq;
import com.EmperorPenguin.SangmyungBank.user.dto.UserPasswordUpdateReq;
import com.EmperorPenguin.SangmyungBank.user.dto.UserRegisterReq;

import com.EmperorPenguin.SangmyungBank.user.entity.User;
import com.EmperorPenguin.SangmyungBank.user.repository.UserRepository;
import com.EmperorPenguin.SangmyungBank.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void 회원가입() {

        // given
        String loginId = "SpringTest1";
        String password1 = "11111111a!";
        String password2 = "11111111a!";
        String name = "Spring";
        String email = "SpringTest@email.com";
        String question = "테스트입니까?";
        String ansWord = "테스트";
        char sex = 'M';
        int age = 13;
        String phoneNumber = "010-1111-1111";

        UserRegisterReq userRegisterReq = UserRegisterReq.builder()
                .loginId(loginId)
                .password1(password1)
                .password2(password2)
                .name(name)
                .email(email)
                .age(age)
                .sex(sex)
                .question(question)
                .ansWord(ansWord)
                .phoneNumber(phoneNumber)
                .build();

        // when
        userService.register(userRegisterReq);
        User findUser = userRepository.findByLoginId(loginId).get();

        // then
        assertThat(findUser.getLoginId()).isEqualTo(loginId);
        passwordEncoder.matches(findUser.getPassword(),password1);
        assertThat(findUser.getName()).isEqualTo(name);
        assertThat(findUser.getEmail()).isEqualTo(email);
        assertThat(findUser.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(findUser.getSex()).isEqualTo(sex);
        assertThat(findUser.getQuestion()).isEqualTo(question);
        assertThat(findUser.getAnsWord()).isEqualTo(ansWord);

    }

    @Test
    void 사용자로그인() {
        UserRegisterReq userRegisterReq = UserRegisterReq.builder()
                .loginId("SpringTest1")
                .password1("11111111a!")
                .password2("11111111a!")
                .name("테스트")
                .email("SpringTest@email.com")
                .age(13)
                .sex('M')
                .phoneNumber("010-1111-1111")
                .question("테스트입니까?")
                .ansWord("네 테스트입니다.")
                .build();
        userService.register(userRegisterReq);

        // given
        String loginId = "SpringTest1";
        String password ="11111111a!";
        UserLoginReq userLoginReq = UserLoginReq.builder()
                .loginId(loginId)
                .password(password)
                .build();

        // when
        User loginUser = userService.login(userLoginReq);
        User dbUser = userRepository.findByLoginId(loginId).get();

        // then
        assertThat(loginUser).isEqualTo(dbUser);

    }

    @Test
    void 사용자비밀번호찾기() {

        UserRegisterReq userRegisterReq = UserRegisterReq.builder()
                .loginId("SpringTest1")
                .password1("11111111a!")
                .password2("11111111a!")
                .name("테스트")
                .email("SpringTest@email.com")
                .age(13)
                .sex('M')
                .phoneNumber("010-1111-1111")
                .question("테스트입니까?")
                .ansWord("네 테스트입니다.")
                .build();
        userService.register(userRegisterReq);

        // given
        String loginId = "SpringTest1";
        String question = "테스트입니까?";
        String ansWord = "네 테스트입니다.";
        UserFindPasswordReq userFindPasswordReq = UserFindPasswordReq.builder()
                .loginId(loginId)
                .question(question)
                .ansWord(ansWord)
                .build();

        // when
        String templatePassword = userService.setTemplatePassword(userFindPasswordReq);
        User dbUser = userRepository.findByLoginId(loginId).get();

        // then
        passwordEncoder.matches(templatePassword, dbUser.getPassword());
    }

    @Test
    void 사용자비밀번호변경(){

        UserRegisterReq userRegisterReq = UserRegisterReq.builder()
                .loginId("SpringTest1")
                .password1("11111111a!")
                .password2("11111111a!")
                .name("테스트")
                .email("SpringTest@email.com")
                .age(13)
                .sex('M')
                .phoneNumber("010-1111-1111")
                .question("테스트입니까?")
                .ansWord("네 테스트입니다.")
                .build();
        userService.register(userRegisterReq);

        String loginId = "SpringTest1";
        String question = "테스트입니까?";
        String ansWord = "네 테스트입니다.";
        UserFindPasswordReq userFindPasswordReq = UserFindPasswordReq.builder()
                .loginId(loginId)
                .question(question)
                .ansWord(ansWord)
                .build();

        // given
        String oldPassword = userService.setTemplatePassword(userFindPasswordReq);
        String newPassword1 = "11111111a!";
        String newPassword2 = "11111111a!";

        UserPasswordUpdateReq userPasswordUpdateReq = UserPasswordUpdateReq.builder()
                .loginId(loginId)
                .oldPassword(oldPassword)
                .newPassword1(newPassword1)
                .newPassword2(newPassword2)
                .build();

        // when
        userService.updateNewPassword(userPasswordUpdateReq);
        User dbUser = userRepository.findByLoginId(loginId).get();

        // then
        passwordEncoder.matches(newPassword1, dbUser.getPassword());
    }
}



