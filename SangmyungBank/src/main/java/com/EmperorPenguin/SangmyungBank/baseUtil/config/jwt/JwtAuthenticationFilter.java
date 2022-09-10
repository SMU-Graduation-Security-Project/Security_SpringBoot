package com.EmperorPenguin.SangmyungBank.baseUtil.config.jwt;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.DateConfig;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.auth.PrincipalDetails;
import com.EmperorPenguin.SangmyungBank.baseUtil.config.service.JwtService;
import com.EmperorPenguin.SangmyungBank.baseUtil.exception.ExceptionMessages;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberLoginReq;
import com.EmperorPenguin.SangmyungBank.member.entity.Member;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
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

// 스프링 시큐리티에서 usernamePasswordAuthenticationFilter가 있음
// /login 요청해서 username, password를 전송하면 (post)
// 이 필터가 동작함.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
//        System.out.println("로그인시도중");

        try{
            ObjectMapper om = new ObjectMapper();

            MemberLoginReq login = om.readValue(request.getInputStream(),MemberLoginReq.class);
            System.out.println(login);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(login.getLoginId(), login.getPassword());

            //PrincipalDetailsService의 loadUserByUsername()함수가 실행됨
            Authentication authentication= authenticationManager.authenticate(authenticationToken);
            PrincipalDetails principalDetails =(PrincipalDetails)authentication.getPrincipal();
            // 권한 관리를 Security가 해주므로 넘겨준다.
            return authentication;

        }catch (IOException e){e.printStackTrace();}

        return null;
    }

    // 인증이 정상적으로 완료되면 실행된다.
    // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해준다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
//        System.out.println("successfulAuthentication 실행됨:인증완료");
        PrincipalDetails principalDetails =(PrincipalDetails)authResult.getPrincipal();
        //RSA가 아닌 Hash암호방식
        String time = new DateConfig().getDateTime();
        Member member = principalDetails.getMember();
        String accessJwt = jwtService.createAccessToken(member.getMemberId(), member.getLoginId());
        String refreshJwt = jwtService.createRefreshToken();

        // login 성공 -> Refresh 토큰 재발급
        jwtService.setRefreshToken(member.getLoginId(), refreshJwt);
        jwtService.updateMemberData(time,member.getLoginId());

        response.addHeader(JwtProperties.HEADER_PREFIX,JwtProperties.TOKEN_PREFIX+ accessJwt);
        response.addHeader(JwtProperties.REFRESH_HEADER_PREFIX,JwtProperties.TOKEN_PREFIX+ refreshJwt);

        setSuccessResponse(response, "로그인 성공", member, time);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String failReason =
                failed.getMessage().equals(ExceptionMessages.ERROR_MEMBER_NOT_FOUND_ENG.getMessage())
                        ? ExceptionMessages.ERROR_MEMBER_NOT_FOUND.getMessage()
                        : ExceptionMessages.ERROR_MEMBER_PASSWORD.getMessage();

        setFailResponse(response, failReason);
    }

    private void setSuccessResponse(HttpServletResponse response, String message, Member res, String time) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("checker", true);
        jsonObject.put("message", message);

        JSONObject data = new JSONObject();
        data.put("loginId", res.getLoginId());
        data.put("name", res.getName());
        data.put("loginDate", time);
        data.put("useTemplatePassword", res.isUsingTempPassword());

        JSONArray req_array = new JSONArray();
        req_array.add(data);

        jsonObject.appendField("data", req_array);
        response.getWriter().print(jsonObject);
    }

    private void setFailResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json;charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("checker", false);
        jsonObject.put("message", message);

        response.getWriter().print(jsonObject);
    }

}