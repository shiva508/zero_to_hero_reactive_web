package com.pool.reactive.mono;

import reactor.core.publisher.Mono;

public class MonoSubscribeClient {
    public static void main(String[] args) {
        Mono<String> hero = Mono.just("Bataman");
        // hero.subscribe();
        // hero.subscribe(he -> System.out.println(he));
        hero.subscribe(na -> System.out.println(na),
                err -> System.out.println(err.getMessage()), () -> System.out.println("Task completed"));
    }
}
