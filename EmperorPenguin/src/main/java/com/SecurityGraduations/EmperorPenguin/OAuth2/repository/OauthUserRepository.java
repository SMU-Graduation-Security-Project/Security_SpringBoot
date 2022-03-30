package com.SecurityGraduations.EmperorPenguin.OAuth2.repository;

import com.SecurityGraduations.EmperorPenguin.OAuth2.domain.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OauthUserRepository extends JpaRepository<OauthUser,Long>{
        Optional<OauthUser> findByEmail(String email); // 이미 email을 통해 생성된 사용자인지 체크
}

