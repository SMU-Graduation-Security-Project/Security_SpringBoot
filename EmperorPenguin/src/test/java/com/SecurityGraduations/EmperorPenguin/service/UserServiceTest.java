package com.SecurityGraduations.EmperorPenguin.service;

import com.SecurityGraduations.EmperorPenguin.Login.domain.LoginForm;
import com.SecurityGraduations.EmperorPenguin.Login.domain.User;
import com.SecurityGraduations.EmperorPenguin.Login.repository.UserRepository;
import com.SecurityGraduations.EmperorPenguin.Login.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
@SpringBootTest
class UserServiceTest {

	@Autowired
	UserService userService;
	@Autowired UserRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;

	@Test
	void 회원가입() {
		// given
		String id = "SpringTest1";
		String rawPassword = "12345";
		// when
		userService.register(id,rawPassword,"Spring", "Spring@email.com");
		// then
		User finduser = userService.findOne(id).get();
		assertThat(id).isEqualTo(finduser.getId());
	}

	@Test
	void 로그인() {
		// given
		String id = "SpringTest2";
		String rawPassword = "12345";
		// when
		userService.register(id, rawPassword, "SpringTest", "Spring2@rmail.com");
		LoginForm loginForm = new LoginForm();
		loginForm.setLoginID(id);
		loginForm.setPassword(rawPassword);
		// then
		userService.authenticate(loginForm);
	}


}
