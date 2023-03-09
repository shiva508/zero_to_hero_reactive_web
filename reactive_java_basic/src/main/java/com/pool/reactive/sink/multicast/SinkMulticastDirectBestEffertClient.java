package com.pool.reactive.sink.multicast;

import java.time.Duration;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SinkMulticastDirectBestEffertClient {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "20");
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();
        Flux<Object> endPoint = sink.asFlux();
        endPoint.subscribe(Util.subscriber("Batman"));
        endPoint.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("Superman"));

        for (int index = 0; index < 100; index++) {
            sink.tryEmitNext(index);
        }
        Util.threadSleep(10000);

    }
}
