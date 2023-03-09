package com.pool.reactive.mono;

import com.pool.Util;

import reactor.core.publisher.Mono;

public class MonoErrorClient {
    public static void main(String[] args) {
        Mono<Integer> error = Mono.just("Bataman").map(String::length).map(len -> len / 0);
        error.subscribe(
                Util.onNext,
                Util.onError,
                Util.onComplete);
    }
}
