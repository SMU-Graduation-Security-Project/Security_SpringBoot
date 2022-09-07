package com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.auth.PrincipalDetails;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.service.JwtService;
import com.EmperorPenguin.SangmyungBank.baseUtil.dto.JwtErrorCode;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.CustomJwtException;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;

import com.auth0.jwt.exceptions.TokenExpiredException;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

// 시큐리티 Filter 가지고 있음 그 필터 중에 BasicAuthenticationFilter.
// 권한이아 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음.
// 만약 권한 인증이 필요한 주소가 아니라면 이 필터를 타지 않는다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private final JwtService jwtService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    // 인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 거친다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소 요청이 됨");

        try {
            jwtService.checkHeaderValid(request);
            String accessJwtToken = request
                    .getHeader(JwtProperties.HEADER_PREFIX)
                    .replace(JwtProperties.TOKEN_PREFIX, "");
            String refreshJwtToken = request
                    .getHeader(JwtProperties.REFRESH_HEADER_PREFIX)
                    .replace(JwtProperties.TOKEN_PREFIX, "");
            jwtService.checkTokenValid(refreshJwtToken);

            System.out.println("리프레쉬 토큰 회원 조회");
            Member memberByRefreshToken = jwtService.getMemberByRefreshToken(refreshJwtToken);
            String loginId = memberByRefreshToken.getLoginId();
            Long id = memberByRefreshToken.getMemberId();


            try {
                System.out.println("액세스 토큰 검증");
                jwtService.checkTokenValid(accessJwtToken);
            } catch (TokenExpiredException expired) {
                System.out.println("ACCESS TOKEN REISSUE : " + JwtErrorCode.JWT_ACCESS_EXPIRED);
                accessJwtToken = jwtService.createAccessToken(id, loginId);
                response.addHeader(JwtProperties.HEADER_PREFIX, JwtProperties.TOKEN_PREFIX + accessJwtToken);
            }

            PrincipalDetails principalDetails = new PrincipalDetails(memberByRefreshToken);
            Authentication auth = new UsernamePasswordAuthenticationToken
                    (principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (CustomJwtException cusJwtExc) {
            request.setAttribute(JwtProperties.EXCEPTION, cusJwtExc.getMessage());
        } catch (TokenExpiredException ee) {
            request.setAttribute(JwtProperties.EXCEPTION, JwtErrorCode.JWT_REFRESH_EXPIRED);
        } catch (MalformedJwtException | UnsupportedJwtException mj) {
            request.setAttribute(JwtProperties.EXCEPTION, JwtErrorCode.JWT_NOT_VALID);
        } catch (Exception e) {
            System.out.println("미정의 에러 : " + e);
            System.out.println(e.getMessage());
            request.setAttribute(JwtProperties.EXCEPTION, JwtErrorCode.JWT_NOT_VALID);
        }

        chain.doFilter(request, response);
    }
}
