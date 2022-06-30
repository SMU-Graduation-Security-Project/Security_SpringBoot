package com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.auth.PrincipalDetails;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberLoginReq;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// 스프링 시큐리티에서 usernamePasswordAuthenticationFilter가 있음
// /login 요청해서 username, password를 전송하면 (post)
// 이 필터가 동작함.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 1. username password 받아서
        // 2. 정상인지 로그인 시도를 해보기. -> AuthenticationManager로 로그인 시도를 하면
        // 3. PrincipalDetails를 세션에 담고(권한 관리를 위해서)
        // 4. JWT 토큰을 만들어서 응답해준다.
        ObjectMapper om = new ObjectMapper();        // Json Data Parsing
        try {
            MemberLoginReq login = om.readValue(request.getInputStream(), MemberLoginReq.class);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(login.getLoginId(), login.getPassword());
            System.out.println(login.getLoginId() + login.getPassword());
            // 권한 관리를 Security가 해주므로 넘겨준다.
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 오류가 발생한 것임.
        return null;
    }

    // 인증이 정상적으로 완료되면 실행된다.
    // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해준다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000*10))) // 만료시간 10m
                .withClaim("id", principalDetails.getMember().getMemberId())
                .withClaim("loginId",principalDetails.getMember().getLoginId())
                .sign(Algorithm.HMAC512("sumung"));
        response.addHeader("Authentication","Bearer"+jwtToken);
    }

}