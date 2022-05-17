package com.EmperorPenguin.SangmyungBank.user.repository;

import com.EmperorPenguin.SangmyungBank.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(Long userId);

    Optional<User> findByLoginId(String loginId);

    Optional<User> findByEmail(String Email);

    Optional<User> findByPhoneNumber(String PhoneNumber);

    @Modifying
    @Query("update User u set u.loginDate = :loginTime where u.loginId =:loginId")
    void updateLoginDate(@Param("loginTime")String loginTime, @Param("loginId")String LoginId);

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
