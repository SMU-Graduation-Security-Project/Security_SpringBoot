package com.SecurityGraduations.EmperorPenguin.OAuth2.dto;

import com.SecurityGraduations.EmperorPenguin.Common.domain.Role;
import com.SecurityGraduations.EmperorPenguin.OAuth2.domain.OauthUser;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes; // Oauth2 반환하는 유저 정보 Map
    private String nameAttributeKey;
    private String name;
    private String email;
    private String id;


    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name,
                           String email, String id)
    {
        this.attributes = attributes;
        this.nameAttributeKey= nameAttributeKey;
        this.name = name;
        this.email = email;
        this.id = id;

    }

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        // 여기서 네이버와 카카오 등 구분(ofNavver, ofKakao).
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    public OauthUser toEntity() {
        return OauthUser.builder()
                .name(name)
                .email(email)
                .role(Role.GUEST)
                .build();
    }
}