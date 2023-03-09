package com.pool.reactive.flux;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class FluxToMonoClient {
    public static void main(String[] args) {
        Flux.range(0, 5).next().subscribe(Util.onNext);
    }
}
