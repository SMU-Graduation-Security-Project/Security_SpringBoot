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
                    // 페이지 접근 권한 설정을 모두 풀어둠 permitall을 통해 하위 주소는 모두 접근 가능.
                    .antMatchers("/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    // oauth2 로그인 설정
                    .oauth2Login()
                    // Oauth2 로그인이 성공했을 떄 가져올 설정들
                    .userInfoEndpoint()
                    // SNS 로그인 이후 진행될 userservice 구현체 등록
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
