package com.pool.reactive.flatmap;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FlatMapClientTest {
    @Test
    public void flatMapTest() {
        Flux<Integer> ids = Flux.just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .flatMap(pair -> FlatMapClient.delayReplyFor(pair));
        StepVerifier.create(ids)
                .expectNext(3, 2, 1)
                .verifyComplete();
    }
}
