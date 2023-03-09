package com.pool.reactive.schedulers;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SchedulersClient {
    public static void main(String[] args) {
        var counter = new AtomicInteger();
        Schedulers.onScheduleHook("my hook", runnable -> () -> {
            var threadName = Thread.currentThread().getName();
            counter.incrementAndGet();
            System.out.println("before execution: " + threadName);
            runnable.run();
            System.out.println("after execution: " + threadName);
        });

        Flux<Integer> integerFlux = Flux.just(1, 2, 3).delayElements(Duration.ofMillis(1))
                .subscribeOn(Schedulers.immediate());

        integerFlux.subscribe(System.out::println);
        Util.threadSleep(1000);
    }
}
