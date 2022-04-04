package com.SecurityGraduations.EmperorPenguin.OAuth2.service;

import com.SecurityGraduations.EmperorPenguin.OAuth2.domain.OauthUser;
import com.SecurityGraduations.EmperorPenguin.OAuth2.dto.OAuthAttributes;
import com.SecurityGraduations.EmperorPenguin.OAuth2.dto.SessionUser;
import com.SecurityGraduations.EmperorPenguin.OAuth2.repository.OauthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpSession;
import java.util.Collections;

@Transactional
@Service
@RequiredArgsConstructor
public class CustomerOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final OauthUserRepository oauthUserRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // OAuth2 서비스 id (구글, 카카오, 네이버)
        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId();
        // OAuth2 로그인 진행 시 키가 되는 필드 값(PK)
        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        // OAuth2UserService
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        OauthUser oauthUser = saveOrUpdate(attributes);
        httpSession.setAttribute("oAuth2User", new SessionUser(oauthUser)); // SessionUser (직렬화된 dto 클래스 사용)

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(oauthUser.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    // 유저 생성 및 수정 서비스 로직
    private OauthUser saveOrUpdate(OAuthAttributes attributes){
        OauthUser user = oauthUserRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());
        return oauthUserRepository.save(user);
    }
}