package com.EmperorPenguin.SangmyungBank.user.repository;

import com.EmperorPenguin.SangmyungBank.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long userId);

    Optional<User> findByLoginId(String loginId);

    Optional<User> findByEmail(String Email);

    Optional<User> findByPhoneNumber(String PhoneNumber);

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
