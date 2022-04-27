package com.EmperorPenguin.SangmyungBank.api.users.login.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserAuthRepository {

    private final EntityManager em;

    public UserAuthRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<User> findById(String loginId)
    {
        List<User> result = em.createQuery("select m from User m where m.loginId = :loginId", User.class)
                .setParameter("loginId",loginId)
                .getResultList();
        return result.stream().findAny();
    }
}
