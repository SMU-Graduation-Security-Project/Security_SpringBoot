package com.EmperorPenguin.SangmyungBank.baseUtil.config;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt.JwtAuthenticationFilter;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt.JwtAuthorizationFilter;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.service.JwtService;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.exceptionHandleClass.CustomAccessDeniedHandler;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.exceptionHandleClass.CustomAuthenticationEntryPoint;
import com.EmperorPenguin.SangmyungBank.member.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;


@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터 체인에 등록된다.
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JwtService jwtService;
    private final OauthService oauthService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers(
                // swagger
                "/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger/**",

                "/image/**",
                // 회원가입
                "api/v1/user/register",

                // Guest 관련
                "/api/v1/guest/**",

                // /oauth/** 있는 모든 파일들은 시큐리티 적용을 무시한다.
                "users/oauthLogin/**"
        );
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()); // 정적인 리소스들에 대해서 시큐리티 적용 무시.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Session을 사용하지 않는다
                .and()
                .addFilter(corsFilter)  //@CrossOrigin(인증X)/ Security Filter 인증(O)
                .formLogin().disable()
                .httpBasic().disable()  //Bearer 방식을 사용할 것이므로
                .addFilter(jwtAuthenticationFilter())  //AutenticationManger를 던져줘야 한다.
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtService))
                .authorizeRequests()
                // LOGIN
                .antMatchers("/api/v1/user/login").permitAll()
                // GUEST
                .antMatchers("/api/v1/guest/**")
                .access("hasRole('ROLE_GUEST') or hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                // User
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_/USER') or hasRole('ROLE_ADMIN')")
                // ADMIN
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll() // 그외 요청 모두 허용
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()   //추가
                .oauth2Login()				// OAuth2기반의 로그인인 경우
                .userInfoEndpoint()			// 로그인 성공 후 사용자정보를 가져온다
                .userService(oauthService);	//사용자정보를 처리할 때 사용한다
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter =
                new JwtAuthenticationFilter(authenticationManager(), jwtService);
        jwtAuthenticationFilter
                .setFilterProcessesUrl("/api/v1/user/login");
        return jwtAuthenticationFilter;
    }
}
