package com.pool.reactive.handle;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;

public class HandleClient {
    public static void main(String[] args) {
        Flux<Integer> nums = handleOperation(5, 4);
        nums.subscribe(System.out::println);
    }

    public static Flux<Integer> handleOperation(int max, int numberToError) {

        return Flux.range(0, max)
                .handle((value, sink) -> {
                    var upTo = Stream.iterate(0, i -> i < numberToError, i -> i + 1)
                            .collect(Collectors.toList());
                    if (upTo.contains(value)) {
                        sink.next(value);
                        return;
                    }
                    if (value == numberToError) {
                        sink.error(new IllegalArgumentException("No 4 for you!"));
                        return;
                    }
                    sink.complete();
                });
    }
}
