package com.EmperorPenguin.SangmyungBank.baseUtil.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
//@Configuration
//@RequiredArgsConstructor
// 직접 만든 TokenProvider 와 JwtFilter 를 SecurityConfig 에 적용할 때 사용
//public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//    private final TokenProvider tokenProvider;
//
//    // TokenProvider 를 주입받아서 JwtFilter 를 통해 Security 로직에 필터를 등록
//    @Override
//    public void configure(HttpSecurity http) {
//        JwtFilter customFilter = new JwtFilter(tokenProvider);
//        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
