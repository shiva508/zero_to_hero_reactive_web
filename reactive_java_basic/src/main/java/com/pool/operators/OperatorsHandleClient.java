package com.pool.operators;

import java.util.function.BiConsumer;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class OperatorsHandleClient {
    public static void main(String[] args) {

        BiConsumer<Integer, SynchronousSink<Integer>> biConsumer = (t, synchronousSink) -> {
            if (t == 10) {
                synchronousSink.complete();
            } else {
                synchronousSink.next(t);
            }
        };
        Flux.range(1, 20).handle(biConsumer).subscribe(Util.subscriber());
        BiConsumer<String, SynchronousSink<String>> countryConsumer = (countryName, synchronousSink) -> {
            if (countryName.equalsIgnoreCase("canada")) {
                synchronousSink.complete();
            } else {
                synchronousSink.next(countryName);
            }
        };
        Flux.generate(synchronousSink -> synchronousSink.next(Util.fakerInstance().country().name()))
                .map(Object::toString)
                .handle(countryConsumer).subscribe(Util.subscriber());
    }

}
