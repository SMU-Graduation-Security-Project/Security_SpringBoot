package com.SecurityGraduations.EmperorPenguin.repository;

import com.SecurityGraduations.EmperorPenguin.domain.User;

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
    public Optional<User> findByID(String Id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
