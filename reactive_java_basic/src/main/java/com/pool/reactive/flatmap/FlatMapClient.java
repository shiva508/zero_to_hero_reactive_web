package com.pool.reactive.flatmap;

import java.time.Duration;
import reactor.core.publisher.Flux;

public class FlatMapClient {
    public static void main(String[] args) {

        Flux<Integer> ids = Flux.just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .flatMap(pair -> delayReplyFor(pair));
        ids.subscribe(System.out::println);

        Flux<Integer> idsconcat = Flux.just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .concatMap(pair -> delayReplyFor(pair));
        idsconcat.subscribe(System.out::println);

        Flux<String> source = Flux //
                .just("re", "rea", "reac", "react", "reactive") //
                .delayElements(Duration.ofMillis(100))//
                .switchMap(name -> lookup(name));

        source.subscribe(System.out::println);

    }

    private static Flux<String> lookup(String word) {
        return Flux.just(word + " -> reactive")
                .delayElements(Duration.ofMillis(500));
    }

    public static Flux<Integer> delayReplyFor(Pair pair) {
        return Flux.just(pair.id()).delayElements(Duration.ofMillis(pair.delay()));
    }
}
