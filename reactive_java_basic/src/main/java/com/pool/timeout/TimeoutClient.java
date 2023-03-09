package com.pool.timeout;

import java.time.Duration;

import org.reactivestreams.Publisher;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class TimeoutClient {
    public static void main(String[] args) {
        orderGenerator().timeout(Duration.ofSeconds(2), fallback()).subscribe(Util.subscriber());
        Util.threadSleep(10000);
    }

    private static Flux<Integer> fallback() {
        return Flux.range(508, 22);
    }

    public static Flux<Integer> orderGenerator() {
        return Flux.range(408, 22).delayElements(Duration.ofSeconds(5));
    }
}
