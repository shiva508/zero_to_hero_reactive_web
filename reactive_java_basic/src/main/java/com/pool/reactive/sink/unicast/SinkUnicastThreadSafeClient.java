package com.pool.reactive.sink.unicast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkUnicastThreadSafeClient {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> endPoint = sink.asFlux();
        List<Object> nums = new ArrayList<>();
        endPoint.subscribe(nums::add);

        /*
         * for (int index = 0; index < 1000; index++) {
         * final int i = index;
         * CompletableFuture.runAsync(() -> {
         * sink.tryEmitNext(i);
         * });
         * }
         */

        for (int index = 0; index < 1000; index++) {
            final int i = index;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(i, (s, e) -> true);
            });
        }

        Util.threadSleep(3000);
        System.out.println(nums.size());
    }
}
