package com.pool.reactive.flux.emmitingitem;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class FluxGenerateWithStateClient {
    public static void main(String[] args) {
        Flux.generate(() -> 1, (counter, sink) -> {
            String country = Util.fakerInstance().country().name();
            sink.next(country);
            if (counter >= 10 || country.toLowerCase().equals("canada")) {
                sink.complete();
            }
            return counter + 1;
        }).take(4).subscribe(Util.subscriber());
    }
}
