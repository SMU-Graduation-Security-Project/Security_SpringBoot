package com.EmperorPenguin.SangmyungBank.api.users.add.web;


import com.EmperorPenguin.SangmyungBank.api.users.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.users.add.domain.repository.UserRepository;
import com.EmperorPenguin.SangmyungBank.api.users.add.service.UserService;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @PostMapping("/users/add")
    public User addUser(@RequestBody User user)
    {
        User savedUser = userService.register(user);
        return savedUser;
    }

}
