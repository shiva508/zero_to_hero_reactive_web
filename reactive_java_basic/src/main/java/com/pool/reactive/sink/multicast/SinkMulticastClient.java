package com.pool.reactive.sink.multicast;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkMulticastClient {
    public static void main(String[] args) {

        // Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();// pleople should be there to
        Flux<Object> endPoint = sink.asFlux();
        // IF ANY on one of the sunscriber receives msg it wont be available for
        // subscibers joined latter

        endPoint.subscribe(Util.subscriber("Clerk"));
        sink.tryEmitNext("Clerk");
        sink.tryEmitNext("Diana");
        endPoint.subscribe(Util.subscriber("Diana"));
        sink.tryEmitNext("Arther");
        endPoint.subscribe(Util.subscriber("Arther"));
        sink.tryEmitNext("War is comming be ready");

        sink.tryEmitNext("Manhathon are you there");
        endPoint.subscribe(Util.subscriber("Manhathon"));

        sink.tryEmitNext("Seems Manhattonhas just joined let me send message againe");
        sink.tryEmitNext("War is comming be ready");

    }

}
