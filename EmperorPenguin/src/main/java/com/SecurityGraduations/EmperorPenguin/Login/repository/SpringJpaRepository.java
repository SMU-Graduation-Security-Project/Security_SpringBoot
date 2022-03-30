package com.SecurityGraduations.EmperorPenguin.Login.repository;

import com.SecurityGraduations.EmperorPenguin.Login.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface SpringJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}