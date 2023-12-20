package com.pool.controller;

import com.pool.entity.UserEntity;
import com.pool.model.Input;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@RestController
@RequestMapping("/web/api")
public class UserReactiveController {
    private final WebClient reactiveWebClient;
    private final WebClient gitWebClient;


    public UserReactiveController(@Qualifier("reactiveWebClient") WebClient reactiveWebClient,
                                  @Qualifier("gitWebClient") WebClient gitWebClient) {
        this.reactiveWebClient = reactiveWebClient;
        this.gitWebClient = gitWebClient;
    }

    @GetMapping("byname/{name}")
    public Mono<UserEntity> getUserEntityByName(@PathVariable String name){
        return reactiveWebClient.get()
                                .uri("/reapi/byname/{name}",name)
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

    @GetMapping("/getrepo/{username}/{token}")
    public Flux<String> listGithubRepositories(@PathVariable("username") String username,@PathVariable("token") String token) {
        return gitWebClient.get()
                .uri("/user/repos?sort={sortField}&direction={sortDirection}",
                        "updated", "desc")
                .header("Authorization", "Basic " + Base64Utils.encodeToString((username + ":" + token).getBytes(UTF_8)))
                .retrieve()
                .bodyToFlux(String.class);
    }

    public void test(){
        Map<String,String> datta = new HashMap<>();
                this.gitWebClient.post()
                .uri("")
                .body(BodyInserters.fromValue(datta))
                .retrieve()
                .bodyToMono(String.class).log();
    }
}
