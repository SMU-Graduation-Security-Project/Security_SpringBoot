package com.EmperorPenguin.SangmyungBank.api.user.add.domain.repository;

import com.EmperorPenguin.SangmyungBank.api.user.add.domain.User.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByLoginId (String loginid);
    Optional<User> findByEmail(String email);
    List<User> findAll();

}

