package com.pool.reactive.transform;

import java.util.concurrent.atomic.AtomicBoolean;

import reactor.core.publisher.Flux;

public class TransformClient {
    public static void main(String[] args) {
        var finished = new AtomicBoolean();
        var letters = Flux.just("shiva", "satish", "ravi")
                .transform(names -> names.doFinally(signal -> finished.set(true)));
        System.out.println("Before subscribing :" + finished);
        letters.subscribe(System.out::println);
        System.out.println("After subscribing :" + finished);
    }
}
