package com.pool.reactive.combinepublisher;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class ZipClient {
    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTieres()).subscribe(Util.subscriber());
    }

    public static Flux<String> getBody() {
        return Flux.range(1, 5).map(body -> "Body");
    }

    public static Flux<String> getEngine() {
        return Flux.range(1, 3).map(body -> "Engine");
    }

    public static Flux<String> getTieres() {
        return Flux.range(1, 10).map(body -> "Tieres");
    }
}
