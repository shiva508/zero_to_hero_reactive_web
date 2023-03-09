package com.pool.reactive.transform;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class TransformClientTest {
    @Test
    public void transformTest() {
        var finished = new AtomicBoolean();
        var names = Flux.just("shiva", "satish", "ravi")
                .transform(nameslist -> nameslist.doFinally(signal -> finished.set(true)));
        StepVerifier.create(names).expectNextCount(3).verifyComplete();
        assertTrue(finished.get());
    }
}
