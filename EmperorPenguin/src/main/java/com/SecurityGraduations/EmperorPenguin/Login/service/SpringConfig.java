package com.SecurityGraduations.EmperorPenguin.Login.service;

import com.SecurityGraduations.EmperorPenguin.Login.repository.JpaUserRepository;
import com.SecurityGraduations.EmperorPenguin.Login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private UserRepository userRepository;
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(em);
    }
}
