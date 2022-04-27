package com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final EntityManager em;

    public UserRepository(EntityManager em)
    {
        this.em = em;
    }

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public Optional<User> findByLoginId(String loginId) {
        List<User> result = em.createQuery("select m from User m where m.loginId = :loginId", User.class)
                .setParameter("loginId",loginId)
                .getResultList();
        return result.stream().findAny();
    }
}
