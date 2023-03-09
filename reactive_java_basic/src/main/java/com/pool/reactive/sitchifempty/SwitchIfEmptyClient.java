package com.pool.reactive.sitchifempty;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class SwitchIfEmptyClient {
    public static void main(String[] args) {
        getOrders().filter(num -> num > 10).switchIfEmpty(calldb()).subscribe(Util.subscriber());
    }

    public static Flux<Integer> getOrders() {
        return Flux.range(0, 10);
    }

    public static Flux<Integer> calldb() {
        return Flux.range(400, 10);
    }
}
