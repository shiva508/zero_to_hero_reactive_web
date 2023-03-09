package com.pool.reactive.thenmany;

import java.util.concurrent.atomic.AtomicInteger;

import reactor.core.publisher.Flux;

public class ThenManyClient {
    public static void main(String[] args) {
        var letters = new AtomicInteger();
        var numbers = new AtomicInteger();
        Flux<String> lettersPublisher = Flux.just("shiva", "satish", "ravi")
                .doOnNext(name -> letters.incrementAndGet());
        Flux<Integer> numbersPublisher = Flux.just(1, 2, 3).doOnNext(num -> numbers.incrementAndGet());
        Flux<Integer> numbersBeforeLetters = lettersPublisher.thenMany(numbersPublisher);
        System.out.println("Before:" + letters.get());
        System.out.println("Before:" + numbers.get());
        // lettersPublisher.subscribe(System.out::println);
        // numbersPublisher.subscribe(System.out::println);
        numbersBeforeLetters.subscribe(System.out::println);
        System.out.println("After1:" + letters.get());
        System.out.println("After1:" + numbers.get());
    }
}
