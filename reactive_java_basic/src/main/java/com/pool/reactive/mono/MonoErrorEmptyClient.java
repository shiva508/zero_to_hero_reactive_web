package com.pool.reactive.mono;

import com.pool.Util;

import reactor.core.publisher.Mono;

public class MonoErrorEmptyClient {

    public static void main(String[] args) {
        userRepository(3).subscribe(Util.onNext, Util.onError, Util.onComplete);
    }

    public static Mono<String> userRepository(int userid) {
        if (userid == 1) {
            return Mono.just(Util.fakerInstance().name().firstName());
        } else if (userid == 2) {
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("No Match found"));
        }
    }
}
