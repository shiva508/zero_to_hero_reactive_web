package com.pool.onerror;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OnErrorClient {
    public static void main(String[] args) {
        Flux.range(0, 10).log().map(i -> 10 / (5 - i))
                // .onErrorReturn(-1)
                // .onErrorResume(err -> fallback())
                .onErrorContinue((err, item) -> {
                    System.out.println("Error caused " + err.getMessage() + " while processig :" + item);
                })
                .subscribe(Util.subscriber());
    }

    public static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.fakerInstance().random().nextInt(408, 508));
    }
}
