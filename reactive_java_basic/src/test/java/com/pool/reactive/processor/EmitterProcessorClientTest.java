package com.pool.reactive.processor;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.test.StepVerifier;

public class EmitterProcessorClientTest {

    @Test
    public void emitterProcessorTest() {
        EmitterProcessor<String> emitterProcessor = EmitterProcessor.create();
        produce(emitterProcessor.sink());
        consumer(emitterProcessor);
    }

    private static void consumer(Flux<String> emitterProcessor) {
        StepVerifier.create(emitterProcessor)
                .expectNext("Shiva")
                .expectNext("Satish")
                .expectNext("Ravi")
                .verifyComplete();
    }

    private static void produce(FluxSink<String> sink) {
        sink.next("Shiva");
        sink.next("Satish");
        sink.next("Ravi");
        sink.complete();
    }
}
