package com.pool.delay;

import java.time.Duration;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class DelayClient {
    public static void main(String[] args) {
        Flux.range(0, 100).log().delayElements(Duration.ofMillis(1)).subscribe(Util.subscriber());
        Util.threadSleep(10000);
    }
}
