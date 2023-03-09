package com.pool.reactive.mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import com.pool.Util;

import reactor.core.publisher.Mono;

public class MonoCallableClient {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> generateName();
        Mono<String> monoSupplier = Mono.fromSupplier(supplier);
        monoSupplier.subscribe(Util.onNext);
        Callable<String> callable = () -> generateName();
        Mono<String> monoCallable = Mono.fromCallable(callable);
        monoCallable.subscribe(Util.onNext);
    }

    public static String generateName() {
        System.out.println("Genrating name.....");
        return Util.fakerInstance().name().firstName();
    }
}
