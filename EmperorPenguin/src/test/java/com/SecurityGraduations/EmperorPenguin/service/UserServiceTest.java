package com.SecurityGraduations.EmperorPenguin.service;

import com.SecurityGraduations.EmperorPenguin.domain.User;
import com.SecurityGraduations.EmperorPenguin.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {

	@Autowired UserService userService;
	@Autowired UserRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;

	@Test
	void 회원가입() {
		// given
		String id = "SpringTest";
		String rawPassword = "12345";
		// when
		userService.register(id,"Spring@mail.com",rawPassword);
		// then
		User finduser = userService.findOne(id).get();
		assertThat(id).isEqualTo(finduser.getId());
	}

	@Test
	void 로그인() {
		// given
		String Id = "SpringTest";
		String rawPassword = "12345";
		// when

		String encodedPassword = passwordEncoder.encode(rawPassword);
		// then




	}


}
