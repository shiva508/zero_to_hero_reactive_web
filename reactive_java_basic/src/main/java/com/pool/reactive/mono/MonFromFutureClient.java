package com.pool.reactive.mono;

import java.util.concurrent.CompletableFuture;

import com.pool.Util;

import reactor.core.publisher.Mono;

public class MonFromFutureClient {
    public static void main(String[] args) {
        Mono.fromFuture(getName()).subscribe(Util.onNext);
        Util.threadSleep(10000);
    }

    public static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> Util.fakerInstance().name().firstName());
    }
}
