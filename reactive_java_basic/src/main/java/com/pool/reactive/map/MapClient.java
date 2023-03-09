package com.pool.reactive.map;

import java.util.function.Consumer;
import java.util.function.Function;

import reactor.core.publisher.Flux;

public class MapClient {
    public static void main(String[] args) {
        Function<String, String> toUpperCase = name -> name.toUpperCase();
        Consumer<String> consumer = name -> System.out.println(name);
        var names = Flux.just("shiva", "satish", "ravi").map(toUpperCase);
        names.subscribe(consumer);
    }
}
