package com.pool.reactive.sink;

import com.pool.Util;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkEmitClient {
    public static void main(String[] args) {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("Shiva"));
        sink.tryEmitValue("shhhhhh");
    }
}
