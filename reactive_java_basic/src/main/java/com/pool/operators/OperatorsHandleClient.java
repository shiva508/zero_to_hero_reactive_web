package com.pool.operators;

import java.util.function.BiConsumer;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class OperatorsHandleClient {
    public static void main(String[] args) {

        BiConsumer<Integer, SynchronousSink> biConsumer = (t, synchronousSink) -> {
            if (t == 10) {
                synchronousSink.complete();
            } else {
                synchronousSink.next(t);
            }
        };
        Flux.range(1, 20).handle(biConsumer::accept).subscribe(Util.subscriber());
        BiConsumer<String, SynchronousSink> countryConsumer = (countryName, synchronousSink) -> {
            if (countryName.toLowerCase().equals("canada")) {
                synchronousSink.complete();
            } else {
                synchronousSink.next(countryName);
            }
        };
        Flux.generate(synchronousSink -> synchronousSink.next(Util.fakerInstance().country().name()))
                .map(Object::toString)
                .handle(countryConsumer::accept).subscribe(Util.subscriber());
    }

}
