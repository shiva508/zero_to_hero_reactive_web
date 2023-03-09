package com.pool.reactive.filter;

import java.util.function.Predicate;

import reactor.core.publisher.Flux;

public class FilterClient {
    public static void main(String[] args) {
        Flux<Integer> numRange = Flux.range(0, 500).take(5);
        Predicate<Integer> divisableBtTwo = num -> num % 2 == 0;
        Flux<Integer> filter = numRange.filter(divisableBtTwo);
        filter.subscribe(System.out::println);
    }
}
