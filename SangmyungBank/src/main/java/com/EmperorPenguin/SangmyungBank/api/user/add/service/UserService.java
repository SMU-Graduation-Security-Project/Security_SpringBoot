package com.EmperorPenguin.SangmyungBank.api.user.add.service;


import com.EmperorPenguin.SangmyungBank.api.user.add.domain.User.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UserService {

    boolean ValidateDuplicateUser(String Id);
    User register(String loginId, String password, String name, String email, int age, char sex, String phoneNumber);
    User authenticate(User loginUser);
    List<User> findUsers();
    Optional<User> findOne(String userId);

}