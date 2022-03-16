package com.SecurityGraduations.EmperorPenguin.service;


import com.SecurityGraduations.EmperorPenguin.repository.JpaUserRepository;
import com.SecurityGraduations.EmperorPenguin.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.persistence.EntityManager;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    private UserRepository userRepository;
    private EntityManager em;

    @Autowired
    public SecurityJavaConfig(EntityManager em) {
        this.em = em;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // cors 방지
                .cors().disable()
                // csrf 방지
                .csrf().disable()
                // 기존 로그인 페이지 삭제
                .formLogin().disable()
                .headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BruteForce attack을 막기 위해 고안된 방식
        // return new Pbkdf2PasswordEncoder();
        // return new Argon2PasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(em);
    }
}
