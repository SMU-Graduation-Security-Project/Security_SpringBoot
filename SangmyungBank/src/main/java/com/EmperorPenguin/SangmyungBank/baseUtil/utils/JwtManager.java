//package com.EmperorPenguin.SangmyungBank.baseUtil.utils;
//
//import com.EmperorPenguin.SangmyungBank.member.dto.MemberLoginReq;
//import com.EmperorPenguin.SangmyungBank.member.dto.OauthAttributes;
//import io.jsonwebtoken.Header;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import java.time.Duration;
//import java.util.Date;
//
//public class JwtManager {
//    public String makeJwtToken(MemberLoginReq req) {
//        Date now = new Date();
//
//        return Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
//                .setIssuer("fresh") // (2)
//                .setIssuedAt(now) // (3)
//                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
//                .claim("id", req.getLoginId()) // (5)
//                .claim("email", req.getLoginId() + "@gmail.com") // (6)
//                .signWith(SignatureAlgorithm.HS256, "secret") // (6)
//                .compact();
//    }
//
//    public String makeJwtOauthToken(OauthAttributes req) {
//        Date now = new Date();
//
//        return Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
//                .setIssuer("fresh") // (2)
//                .setIssuedAt(now) // (3)
//                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
//                .claim("id", req.getMemberId()) // (5)
//                .claim("email", req.getEmail()) // (6)
//                .signWith(SignatureAlgorithm.HS256, "secret") // (6)
//                .compact();
//    }
//
//}