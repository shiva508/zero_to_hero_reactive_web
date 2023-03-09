package com.pool.reactive.repeat;

import java.util.concurrent.atomic.AtomicInteger;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class RepeatClient {

    public static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        numberStream()
                // .repeat(4)
                .repeat(() -> atomicInteger.get() < 88)
                .subscribe(Util.subscriber());
    }

    public static Flux<Integer> numberStream() {
        return Flux.range(0, 9).doOnSubscribe(num -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("**Completed"))
                .map(num -> atomicInteger.incrementAndGet());
    }
}
