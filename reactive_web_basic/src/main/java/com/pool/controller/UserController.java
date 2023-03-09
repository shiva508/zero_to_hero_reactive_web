package com.pool.controller;

import com.pool.entity.UserEntity;
import com.pool.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("byname/{name}")
    public UserEntity getUserEntityByName(@PathVariable String name){
        return userService.getUserByName(name);
    }

    @GetMapping("all")
    public List<UserEntity> getAllUserEntity(){
        return userService.getAllUsers();
    }
}
