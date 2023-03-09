package com.pool.reactive.sink;

import com.pool.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkEmitFailuereClient {
    public static void main(String[] args) {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber());

        sink.emitValue("Hey Shiva", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        });

        sink.emitValue("Something went wrong", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return true;
        });
    }
}
