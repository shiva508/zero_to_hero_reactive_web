package com.pool.reactive.batching;

import java.time.Duration;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class BufferOverlapAndDrop {

    public static void main(String[] args) {
        events()
                .buffer(8, 1)
                .subscribe(Util.subscriber());
        Util.threadSleep(100000);
    }

    public static Flux<String> events() {
        return Flux.interval(Duration.ofMillis(300))
                .map(num -> "Event " + num);
    }
}
