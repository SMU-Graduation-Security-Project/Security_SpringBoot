package com.SecurityGraduations.EmperorPenguin.Common.service;
import com.SecurityGraduations.EmperorPenguin.Common.domain.Role;
import com.SecurityGraduations.EmperorPenguin.OAuth2.service.CustomerOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    private final CustomerOAuth2UserService customerOAuth2UserService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // cors 방지
                .cors().disable()
                // csrf 방지
                .csrf().disable()
                // 기존 로그인 페이지 삭제
                .formLogin().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customerOAuth2UserService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BruteForce attack을 막기 위해 고안된 방식
        // return new Pbkdf2PasswordEncoder();
        // return new Argon2PasswordEncoder();
        return new BCryptPasswordEncoder();
    }
}
