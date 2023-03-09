package com.pool.reactive.processor;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.ReplayProcessor;
import reactor.test.StepVerifier;

public class ReplayProcessorClientTest {

    @Test
    public void replayProcessorTest() {
        ReplayProcessor<String> processor = ReplayProcessor.create(1, false);
        produce(processor.sink());
        consume(processor);
    }

    private static void consume(Flux<String> processor) {
        for (int index = 0; index < 5; index++) {
            StepVerifier.create(processor).expectNext("ravi").verifyComplete();
        }

    }

    private static void produce(FluxSink<String> sink) {

        sink.next("shiva");
        sink.next("satish");
        sink.next("ravi");
        sink.complete();
    }
}
