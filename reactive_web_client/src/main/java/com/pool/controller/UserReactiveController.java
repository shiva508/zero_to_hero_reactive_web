package com.pool.controller;

import com.pool.entity.UserEntity;
import com.pool.model.Input;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/web/api")
public class UserReactiveController {
    private final WebClient reactiveWebClient;


    public UserReactiveController(WebClient reactiveWebClient) {
        this.reactiveWebClient = reactiveWebClient;
    }

    @GetMapping("byname/{name}")
    public Mono<UserEntity> getUserEntityByName(@PathVariable String name){
        return reactiveWebClient.get().uri("/reapi/byname/{name}",name)
                .retrieve()
                .bodyToMono(UserEntity.class);
    }

    @GetMapping("all")
    public Flux<UserEntity> getAllUserEntity(){
        return null;
    }

    @GetMapping(value = "reall",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserEntity> getAllUserEntityReactive(){
        return null;
    }

    @PostMapping("/createUser")
    public Mono<UserEntity> createUser(@RequestBody Mono<Input> inputMono,
                                       @RequestHeader Map<String,String> headers){
        System.out.println(headers);

        return null;
    }
}
