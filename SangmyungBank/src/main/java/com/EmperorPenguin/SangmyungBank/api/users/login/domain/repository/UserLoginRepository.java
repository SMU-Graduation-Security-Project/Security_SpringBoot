package com.EmperorPenguin.SangmyungBank.api.users.login.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.users.register.domain.User.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserLoginRepository {

    private final EntityManager em;

    public UserLoginRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<User> findByLoginId(String loginId)
    {
        List<User> result = em.createQuery("select m from User m where m.loginId = :loginId", User.class)
                .setParameter("loginId",loginId)
                .getResultList();
        return result.stream().findAny();
    }

}
