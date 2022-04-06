package com.EmperorPenguin.SangmyungBank.api.user.add.web.User;


import com.EmperorPenguin.SangmyungBank.api.user.add.domain.User.User;
import com.EmperorPenguin.SangmyungBank.api.user.add.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("user")User user){
        return "user/addUser";}

    @PostMapping("/addUser")
    public String save(@Valid @ModelAttribute User user , BindingResult result){
        if (result.hasErrors()){
            return  "user/addUser";
        }
        userRepository.save(user);
        return "redirect:/";
    }
}
