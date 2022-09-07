package com.EmperorPenguin.SangmyungBank.member.controller;

import com.EmperorPenguin.SangmyungBank.baseUtil.config.service.JwtService;
import com.EmperorPenguin.SangmyungBank.baseUtil.dto.BaseResult;
import com.EmperorPenguin.SangmyungBank.baseUtil.service.ResponseService;
import com.EmperorPenguin.SangmyungBank.member.dto.MemberLoginReq;
import com.EmperorPenguin.SangmyungBank.member.repository.MemberRepository;
import com.EmperorPenguin.SangmyungBank.member.service.MemberService;
import com.EmperorPenguin.SangmyungBank.member.service.OauthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Map;

@Api(tags = "01. 사용자")
@RequiredArgsConstructor
@RestController
public class OAuthLoginController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUri;

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ResponseService responseService;

    @GetMapping(path = "/login/oauth2/code/google")
    public void GoogleSignCallback(HttpServletRequest request, HttpServletResponse response) {
        try {
            // TODO Auto-generated method stub
            String code = request.getParameter("code");
            HttpHeaders headers = new HttpHeaders();
            RestTemplate restTemplate = new RestTemplate();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
            parameters.add("code", code);
            parameters.add("client_id", clientId);
            parameters.add("client_secret", clientSecret);
            parameters.add("redirect_uri", redirectUri);
            parameters.add("grant_type", "authorization_code");

            HttpEntity<MultiValueMap<String,String>> rest_request = new HttpEntity<>(parameters,headers);

            URI uri = URI.create("https://www.googleapis.com/oauth2/v4/token");

            JwtService jwtService = new JwtService(memberRepository);

            ResponseEntity<String> rest_reponse;
            rest_reponse = restTemplate.postForEntity(uri, rest_request, String.class);
            String bodys = rest_reponse.getBody();
            jwtService.createOAuthAccessToken(bodys);
            System.out.println(bodys);

            response.sendRedirect("http://localhost:9000/success");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




    public BaseResult authOAuthUser(@RequestBody OAuth2UserRequest oAuth2UserRequest){
        // 이전 boolean 통해 오류를 검출하는 방식으로 작동
        // refactoring 이후 Exception 통한 예외처리로 로직 변경
        // 없는 아이디, 잘못된 비밀번호에서 오류 발생.

        MemberLoginReq memberLoginReq = new MemberLoginReq();
        OauthService oauthService = new OauthService();
        DefaultOAuth2User dor = oauthService.loadUser(oAuth2UserRequest);
        memberLoginReq.setLoginId(dor.getAttribute("email"));

        try{
            return responseService.singleResult(memberService.oauthLogin(memberLoginReq).toLoginDto());
        }catch (Exception e){
            return responseService.failResult(
                    e.getMessage()
            );
        }
    }
}