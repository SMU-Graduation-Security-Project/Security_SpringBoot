package com.EmperorPenguin.SangmyungBank.baseUtil.config;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt.JwtAuthenticationFilter;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt.JwtAuthorizationFilter;
import com.EmperorPenguin.SangmyungBank.baseUtil.filter.BankFilterBeforeBasicAuth;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터 체인에 등록된다.
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final MemberRepository memberRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/image/**"); // /image/** 있는 모든 파일들은 시큐리티 적용을 무시한다.
        web.ignoring().requestMatchers(); // 정적인 리소스들에 대해서 시큐리티 적용 무시.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new BankFilterBeforeBasicAuth(), BasicAuthenticationFilter.class);
        http.csrf().disable();
        http.
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Session을 사용하지 않는다
                .and()
                .addFilter(corsFilter)  //@CrossOrigin(인증X)/ Security Filter 인증(O)
                .formLogin().disable()
                .httpBasic().disable()  //Bearer 방식을 사용할 것이므로
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))  //Autentication Manger를 던져줘야 한다.
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository))
                .authorizeRequests()
                // LOGIN
                .antMatchers("/users/login/**").permitAll()
                // USER
                .antMatchers("/users/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                // ADMIN
                .antMatchers("/users/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();


    }
}
