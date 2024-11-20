package com.wings.may24.controller;

import com.wings.may24.entity.UserEntity;
import com.wings.may24.model.request.UserDetailsModel;
import com.wings.may24.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @PostMapping
    public String createUser(@RequestBody UserDetailsModel userDetailsModel){
        userService.createUser(userDetailsModel);

        return "User - " +
                userDetailsModel.getUserId() +
                " is created.";
    }

    @GetMapping
    public List<UserEntity> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/auth")
    public String authorizedOnly(){
        return "Accessing authorized only";
    }
}
