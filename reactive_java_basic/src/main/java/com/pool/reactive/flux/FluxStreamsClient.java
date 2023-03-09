package com.pool.reactive.flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class FluxStreamsClient {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3);
        Stream<Integer> intStream = intList.stream();
        // intStream.forEach(System.out::println);
        // intStream.forEach(System.out::println);// On trying to execute gets errror as
        // first stream closed
        // Flux<Integer> immulat = Flux.fromStream(intStream);
        // Flux<Integer> immulat = Flux.fromStream(() -> intStream);
        Flux<Integer> immulat = Flux.fromStream(() -> intList.stream());
        immulat.subscribe(Util.onNext, Util.onError, Util.onComplete);
        immulat.subscribe(Util.onNext, Util.onError, Util.onComplete);
    }
}
