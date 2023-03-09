package com.pool.reactive.combinepublisher;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class ConcatClient {
    public static void main(String[] args) {
        Flux<String> tierOne = Flux.just("Batman", "Superman");
        Flux<String> tierError = Flux.error(new RuntimeException("Flash"));
        Flux<String> tierTwo = Flux.just("Auther", "Dayana");

        // Flux<String> concatHeros = tierOne.concatWith(tierTwo);
        // Flux<String> concatHeros = Flux.concat(tierOne, tierError, tierTwo);
        Flux<String> concatHeros = Flux.concatDelayError(tierOne, tierError, tierTwo);
        concatHeros.subscribe(Util.subscriber());
    }
}
