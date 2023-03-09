package com.pool.reactive.threading;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParallelExecutionClient {
    public static void main(String[] args) {
        Flux.range(0, 10).parallel().runOn(Schedulers.parallel()).doOnNext(num -> printThreadInfo(" next " + num))
                .subscribe(v -> printThreadInfo(" Sub " + v));
    }

    public static void printThreadInfo(String msg) {
        System.out.println(msg + " Thread " + Thread.currentThread().getName());
    }
}
