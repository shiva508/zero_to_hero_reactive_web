package com.pool.reactive.batching;

import java.time.Duration;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class BufferClient {
    public static void main(String[] args) {
        events()
                // .buffer(8)//Buffer size
                // .buffer(Duration.ofSeconds(2))// with time duriation
                .bufferTimeout(5, Duration.ofSeconds(2))
                .subscribe(Util.subscriber());
        Util.threadSleep(100000);
    }

    public static Flux<String> events() {
        return Flux.interval(Duration.ofMillis(300))
                // .take(5)// to set complete signal
                .map(num -> "Event " + num);
    }
}
