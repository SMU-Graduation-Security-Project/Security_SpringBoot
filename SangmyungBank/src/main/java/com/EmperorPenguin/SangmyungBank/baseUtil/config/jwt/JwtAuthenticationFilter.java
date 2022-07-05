package com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.auth.PrincipalDetails;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberLoginReq;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
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
import java.io.BufferedReader;
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
        System.out.println("로그인시도중");

        try{
            ObjectMapper om = new ObjectMapper();

            MemberLoginReq login = om.readValue(request.getInputStream(),MemberLoginReq.class);
            System.out.println(login);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(login.getLoginId(), login.getPassword());

            //PrincipalDetailsService의 loadUserByUsername()함수가 실행됨
            Authentication authentication= authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails =(PrincipalDetails)authentication.getPrincipal();
            System.out.println(principalDetails.getMember().getLoginId());
            System.out.println("1===========================");
            // 권한 관리를 Security가 해주므로 넘겨준다.
            return authentication;

        }catch (IOException e){e.printStackTrace();}
        System.out.println("2====================================");

        return null;
    }

    // 인증이 정상적으로 완료되면 실행된다.
    // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해준다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행됨:인증완료");
        PrincipalDetails principalDetails =(PrincipalDetails)authResult.getPrincipal();
        //RSA가 아닌 Hash암호방싯
        String jwtToken=JWT.create()
                .withSubject((principalDetails.getUsername()))
                .withExpiresAt(new Date(System.currentTimeMillis()+(60000*10)))
                .withClaim("memberId",principalDetails.getMember().getMemberId())
                .withClaim("username",principalDetails.getMember().getLoginId())
                .sign(Algorithm.HMAC512("sumung"));

        response.addHeader("Autorization","Bearer"+jwtToken);
    }

}