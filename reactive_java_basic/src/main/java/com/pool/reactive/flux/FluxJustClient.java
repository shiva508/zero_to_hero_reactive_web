package com.pool.reactive.flux;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class FluxJustClient {
    public static void main(String[] args) {
        Flux<Integer> numbs = Flux.just(1, 2, 3);
        numbs.subscribe(Util.onNext, Util.onError, Util.onComplete);
        Flux<Integer> empty = Flux.empty();
        empty.subscribe(Util.onNext, Util.onError, Util.onComplete);
    }
}
