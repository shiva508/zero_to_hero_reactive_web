package com.pool.reactive.batching;

import java.time.Duration;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WindowClient {
    public static void main(String[] args) {
        eventGenerator()
                .window(8)// CREATS Flux within flux
                .flatMap(event -> saveEvent(event))
                .subscribe(Util.subscriber());
        Util.threadSleep(10000);
    }

    public static Flux<String> eventGenerator() {
        return Flux.interval(Duration.ofMillis(100)).map(count -> "Event :" + count);
    }

    public static Mono<Void> saveEvent(Flux<String> flux) {
        return flux.doOnNext(event -> System.out.println(event))
                .doOnComplete(() -> {
                    System.out.println("Saved this batch :");
                    System.out.println("___________________");
                }).then();
    }
}
