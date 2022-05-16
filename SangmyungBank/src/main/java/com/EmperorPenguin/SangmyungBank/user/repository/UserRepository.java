package com.EmperorPenguin.SangmyungBank.user.repository;

import com.EmperorPenguin.SangmyungBank.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

}
