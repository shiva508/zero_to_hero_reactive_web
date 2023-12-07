package com.pool.util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Stream;

public class ReactiveSource {

    public static Flux<String> stringNumbersFlux(){
        return Flux.just("one","two","three","four","five","six","seven","eight","nine","ten")
                   .delayElements(Duration.ofSeconds(1));
    }

    public static Flux<Integer> intNumberFlux(){
        return Flux.range(1,10)
                   .delayElements(Duration.ofSeconds(1));
    }

    public static Flux<Integer> integerFluxWithException(){
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .map(integer -> {
                    if (integer == 5){
                        throw new RuntimeException("I have issue with this number "+integer);
                    }
                     return integer;
                });
    }

    public static Mono<Integer> integerMono(){
        return Mono.just(408)
                   .delayElement(Duration.ofSeconds(1));
    }
    public static Flux<User> userFlux(){
        return Flux.just(new User(1,"Teena","Jhones"),
                         new User(1,"Martha","Wyane"),
                         new User(2,"Batman","Bruce"),
                         new User(3,"Banner","Bruce"),
                         new User(4,"Agent","Closen"),
                         new User(5,"Captan","America"),
                         new User(6,"Deadshot","Shooter"),
                         new User(7,"Krish","Krishna murithi"),
                         new User(8,"Doctor","Strange"),
                         new User(9,"Thore","Ragnarok"),
                         new User(10,"Star","Lord"));
    }
}
