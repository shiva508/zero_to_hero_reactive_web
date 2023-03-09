package com.pool.reactive.flux;

import reactor.core.publisher.Flux;

public class FluxMultipleSubscribers {
    public static void main(String[] args) {
        Flux<Integer> intStream = Flux.just(1, 2, 3, 4, 5, 6);
        Flux<Integer> evenIntStream = intStream.filter(num -> num % 2 == 0);
        intStream.subscribe(t1 -> System.out.println("Sub1 :" + t1));
        intStream.subscribe(t2 -> System.out.println("Sub2 :" + t2));
        evenIntStream.subscribe(t3 -> System.out.println("Even :" + t3));
    }
}
