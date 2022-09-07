package com.EmperorPenguin.SangmyungBank.baseUtil.config.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt.JwtProperties;
import com.EmperorPenguin.SangmyungBank.baseUtil.dto.JwtErrorCode;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.CustomJwtException;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
@Getter
@RequiredArgsConstructor
public class JwtService {


    private final MemberRepository memberRepository;

    public String createAccessToken(Long id, String username) {
        return JWT.create()
                .withSubject(JwtProperties.ACCESS_TOKEN)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 만료시간 10m
                .withHeader(createHeader())
                .withClaim(JwtProperties.ID, id)
                .withClaim(JwtProperties.USERID, username)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    public String createOAuthAccessToken(String token){
        return JWT.create()
                .withSubject(JwtProperties.ACCESS_TOKEN)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 만료시간 10m
                .withHeader(createHeader())
                .withClaim(JwtProperties.ID, token)
                .withClaim(JwtProperties.USERID, token)
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    private Map<String, Object> createHeader() {
        return Map.of(JwtProperties.HEADER_PREFIX, "JWT" + JwtProperties.ACCESS_TOKEN);
    }

    public String createRefreshToken() {
        return JWT.create()
                .withSubject(JwtProperties.REFRESH_TOKEN)
                .withHeader(createRefreshHeader())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REFRESH_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    private Map<String, Object> createRefreshHeader() {
        return Map.of(JwtProperties.HEADER_PREFIX, "JWT" + JwtProperties.REFRESH_TOKEN);
    }


    @Transactional(readOnly = true)
    public Member getMemberByRefreshToken(String token) {
        return memberRepository.findByRefreshToken(token)
                .orElseThrow(() -> new CustomJwtException(JwtErrorCode.JWT_REFRESH_EXPIRED.getMessage()));
    }

    @Transactional
    public void setRefreshToken(String username, String refreshJwt) {
        memberRepository.findByLoginId(username)
                .ifPresent(member -> member.setRefreshToken(refreshJwt));
    }

    @Transactional
    public void removeRefreshToken(String token) {
        memberRepository.findByRefreshToken(token)
                .ifPresent(member -> member.setRefreshToken(null));
    }

    @Transactional
    public void logout(HttpServletRequest request) {
        try {
            checkHeaderValid(request);
            String refreshJwtToken = request
                    .getHeader(JwtProperties.REFRESH_HEADER_PREFIX)
                    .replace(JwtProperties.TOKEN_PREFIX, "");
            removeRefreshToken(refreshJwtToken);
        } catch (Exception e) {
            throw new CustomJwtException(JwtErrorCode.JWT_REFRESH_NOT_VALID.name());
        }
    }

    public void checkHeaderValid(HttpServletRequest request) {
        String accessJwt = request.getHeader(JwtProperties.HEADER_PREFIX);
        String refreshJwt = request.getHeader(JwtProperties.REFRESH_HEADER_PREFIX);

        if (accessJwt == null) {
            throw new CustomJwtException(JwtErrorCode.JWT_ACCESS_NOT_VALID.getMessage());
        } else if (refreshJwt == null) {
            throw new CustomJwtException(JwtErrorCode.JWT_REFRESH_NOT_VALID.getMessage());
        }
    }

    public void checkTokenValid(String token) {
        JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
                .build()
                .verify(token);
    }
}
