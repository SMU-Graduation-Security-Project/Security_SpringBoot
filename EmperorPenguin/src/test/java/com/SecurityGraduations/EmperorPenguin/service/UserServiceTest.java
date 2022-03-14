package com.SecurityGraduations.EmperorPenguin.service;

import com.SecurityGraduations.EmperorPenguin.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

	@Autowired UserService userService;
	@Autowired UserRepository userRepository;

	@Test
	void 회원가입() {
		// give
		String Id = "test";
		String rawPassword = "12345";
		String encodedPassword = passwordEncoder.encode(rawPassword);
		// when

		// then
	}

	@Test
	void 로그인() {
		// given

		// when

		// then
	}


}
