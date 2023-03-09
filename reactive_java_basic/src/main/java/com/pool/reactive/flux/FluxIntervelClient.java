package com.pool.reactive.flux;

import java.time.Duration;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class FluxIntervelClient {
    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1)).subscribe(Util.onNext);
        Util.threadSleep(100000);
    }
}
