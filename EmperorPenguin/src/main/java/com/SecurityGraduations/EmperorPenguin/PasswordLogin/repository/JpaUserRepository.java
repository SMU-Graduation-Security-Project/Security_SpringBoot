package com.SecurityGraduations.EmperorPenguin.PasswordLogin.repository;

import com.SecurityGraduations.EmperorPenguin.PasswordLogin.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class JpaUserRepository implements UserRepository{

    private final EntityManager em;

    public JpaUserRepository(EntityManager em)
    {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        List<User> result = em.createQuery("select m from User m where m.id = :id", User.class)
                .setParameter("id",id)
                .getResultList();
        return result.stream().findAny();

    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select m from User  m", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> result = em.createQuery("select m from User m where m.email = :email", User.class)
                .setParameter("email",email)
                .getResultList();
        return result.stream().findAny();
    }
}
