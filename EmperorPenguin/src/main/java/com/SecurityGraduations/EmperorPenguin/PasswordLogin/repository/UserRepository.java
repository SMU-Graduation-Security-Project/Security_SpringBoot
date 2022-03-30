package com.SecurityGraduations.EmperorPenguin.PasswordLogin.repository;
import com.SecurityGraduations.EmperorPenguin.PasswordLogin.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById (String id);

    List<User> findAll();
    Optional<User> findByEmail(String email);
}
