package com.EmperorPenguin.SangmyungBank.baseUtil.config.service;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt.JwtProperties;
import com.EmperorPenguin.SangmyungBank.baseUtil.dto.JwtErrorCode;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.BaseException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.CustomJwtException;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
@Getter
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private final MemberRepository memberRepository;

    public String createAccessToken(Long id, String loginId) {
        return JWT.create()
                .withSubject(JwtProperties.ACCESS_TOKEN)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 만료시간 10m
                .withHeader(createHeader())
                .withClaim(JwtProperties.ID, id)
                .withClaim(JwtProperties.USERID, loginId)
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public String createOAuthAccessToken(String token){
        return JWT.create()
                .withSubject(JwtProperties.ACCESS_TOKEN)
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) // 만료시간 10m
                .withHeader(createHeader())
                .withClaim(JwtProperties.ID, token)
                .withClaim(JwtProperties.USERID, token)
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    private Map<String, Object> createHeader() {
        return Map.of(JwtProperties.HEADER_PREFIX, "JWT" + JwtProperties.ACCESS_TOKEN);
    }

    public String createRefreshToken() {
        return JWT.create()
                .withSubject(JwtProperties.REFRESH_TOKEN)
                .withHeader(createRefreshHeader())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REFRESH_EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    private Map<String, Object> createRefreshHeader() {
        return Map.of(JwtProperties.HEADER_PREFIX, "JWT" + JwtProperties.REFRESH_TOKEN);
    }


    @Transactional(readOnly = true)
    public Member getMemberByRefreshToken(String token) {
        return memberRepository.findByRefreshToken(token)
                .orElseThrow(() -> new CustomJwtException(JwtErrorCode.JWT_REFRESH_EXPIRED.getMessage()));
    }

    // Not Used
    @Transactional
    public Member getMemberByToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        String loginId = claims.get("loginId", String.class);
        return memberRepository.findByLoginId(loginId)
                .orElseThrow(() ->new BaseException(ExceptionMessages.ERROR_MEMBER_NOT_FOUND));
    }

    @Transactional
    public void setRefreshToken(String loginId, String refreshJwt) {
        memberRepository.findByLoginId(loginId)
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
        JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .build()
                .verify(token);
    }
}
