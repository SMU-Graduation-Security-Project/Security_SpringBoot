package com.SecurityGraduations.EmperorPenguin.OAuth2.dto;

import com.SecurityGraduations.EmperorPenguin.OAuth2.domain.OauthUser;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;


    public SessionUser(OauthUser oauthuser) {
        this.name = oauthuser.getName();
        this.email = oauthuser.getEmail();
    }
}