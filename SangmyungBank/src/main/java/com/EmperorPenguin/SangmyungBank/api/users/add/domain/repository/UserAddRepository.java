package com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserAddRepository {

    private final EntityManager em;

    public UserAddRepository(EntityManager em) {
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

    public boolean RepeatEmailCheck(String email)
    {
        List<User> result = em.createQuery("select m from User m where m.email = :email", User.class)
                .setParameter("email",email)
                .getResultList();
        if (result.stream().findAny().isEmpty())
            return true;
        else
            return false;
    }

    public boolean RepeatPhoneNumber(String phoneNumber)
    {
        List<User> result = em.createQuery("select m from User m where m.phoneNumber = :phoneNumber", User.class)
                .setParameter("phoneNumber",phoneNumber)
                .getResultList();
        if (result.stream().findAny().isEmpty())
            return true;
        else
            return false;
    }
}
