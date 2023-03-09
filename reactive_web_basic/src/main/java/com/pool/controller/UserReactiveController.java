package com.pool.controller;

import com.pool.entity.UserEntity;
import com.pool.model.Input;
import com.pool.service.UserReactiveService;
import com.pool.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reapi")
public class UserReactiveController {
    private final UserReactiveService userReactiveService;

    public UserReactiveController(UserReactiveService userReactiveService) {
        this.userReactiveService = userReactiveService;
    }

    @GetMapping("byname/{name}")
    public Mono<UserEntity> getUserEntityByName(@PathVariable String name){
        return userReactiveService.getUserByName(name);
    }

    @GetMapping("all")
    public Flux<UserEntity> getAllUserEntity(){
        return userReactiveService.getAllUsers();
    }

    @GetMapping(value = "reall",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserEntity> getAllUserEntityReactive(){
        return userReactiveService.getAllUsers();
    }

    @PostMapping("/createUser")
    public Mono<UserEntity> createUser(@RequestBody Mono<Input> inputMono,
                                       @RequestHeader Map<String,String> headers){
        System.out.println(headers);

        return userReactiveService.getCreateUser(inputMono);
    }
}
