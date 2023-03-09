package com.pool.service;

import com.pool.entity.UserEntity;
import com.pool.exception.UserException;
import com.pool.model.Input;
import com.pool.util.UserUtil;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class UserReactiveService {

    public static Flux<UserEntity> userEntityFlux=null;
    
    static {
        userEntityFlux=Flux.just(
        new UserEntity("A"),
        new UserEntity("B"),
        new UserEntity("C"),
        new UserEntity("D"),
        new UserEntity("E"),
        new UserEntity("F"),
        new UserEntity("G"),
        new UserEntity("H"),
        new UserEntity("I"),
        new UserEntity("J"),
        new UserEntity("K"),
        new UserEntity("L"),
        new UserEntity("M"),
        new UserEntity("N"));
    }

    public Mono<UserEntity> getUserByName(String name){
      return userEntityFlux.filter(userEntity -> userEntity.getName().equals(name)).single();
    }

    public Flux<UserEntity> getAllUsers(){
       return userEntityFlux.delayElements(Duration.ofSeconds(1))
                            .doOnNext(userEntity -> System.out.println("Processing " +userEntity.getName()))
                                .doOnNext(userEntity -> userEntity.setName(userEntity.getName()+new Random().nextInt()));
    }
    public Mono<UserEntity> getCreateUser(Mono<Input> inputMono) {
       return inputMono.handle((input, synchronousSink) -> {
           if(input.getName().length()>2){
               synchronousSink.next(input);
           }else{
               throw new UserException("Please Provide full name");
           }
       }).cast(Input.class).map(input -> new UserEntity(input.getName()));
    }
}
