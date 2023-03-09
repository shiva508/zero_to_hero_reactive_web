package com.pool.reactive.sink.unicast;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkUnicastClient {
    public static void main(String[] args) {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> endPoind = sink.asFlux();/// through wich subscribers can receive data

        endPoind.subscribe(Util.subscriber("Batman"));// Subscriber One

        // What happens Flash try to receive daa

        endPoind.subscribe(Util.subscriber("Flash"));// he wont receive

        sink.tryEmitNext("Clerk");
        sink.tryEmitNext("Diana");
        sink.tryEmitNext("Arther");
        sink.tryEmitNext("War is comming be ready");

    }
}
