package com.pool.reactive.flux;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class FluxRangeClient {
    public static void main(String[] args) {
        Flux.range(0, 10).subscribe(Util.onNext);
        Flux.range(0, 10).log().map(t -> Util.fakerInstance().name().firstName()).log().subscribe(Util.onNext);
    }
}
