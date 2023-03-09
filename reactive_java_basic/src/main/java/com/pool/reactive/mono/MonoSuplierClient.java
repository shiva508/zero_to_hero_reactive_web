package com.pool.reactive.mono;

import com.pool.Util;

import reactor.core.publisher.Mono;

public class MonoSuplierClient {
    public static void main(String[] args) {
        Mono<String> monoJust = Mono.just(generateName());
        Mono<String> monoSupplier = Mono.fromSupplier(() -> generateName());
        monoSupplier.subscribe(Util.onNext);
    }

    public static String generateName() {
        System.out.println("Genrating name.....");
        return Util.fakerInstance().name().firstName();
    }
}
