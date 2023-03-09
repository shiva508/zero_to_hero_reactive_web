package com.pool.reactive.combinepublisher.helper;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class QuatorFlight {
    public static Flux<String> getFlights() {
        return Flux.range(1, Util.fakerInstance().random().nextInt(1, 5))
                .map(num -> " Quator-" + Util.fakerInstance().random().nextInt(407, 509))
                .filter(flightName -> Util.fakerInstance().random().nextBoolean());
    }
}
