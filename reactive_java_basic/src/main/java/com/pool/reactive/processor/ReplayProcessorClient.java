package com.pool.reactive.processor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.ReplayProcessor;

public class ReplayProcessorClient {
    public static void main(String[] args) {
        ReplayProcessor<String> processor = ReplayProcessor.create(1, false);
        produce(processor.sink());
        consume(processor);
    }

    private static void consume(Flux<String> processor) {

        processor.subscribe(System.out::println);
    }

    private static void produce(FluxSink<String> sink) {
        sink.next("shiva");
        sink.next("satish");
        sink.next("ravi");
        sink.complete();
    }
}
