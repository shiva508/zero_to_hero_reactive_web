package com.pool.reactive.threading;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublishOnClient {

    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadInfo(" create ");
            for (int index = 0; index < 4; index++) {
                fluxSink.next(index + 2);
            }
            fluxSink.complete();
        }).doOnNext(num -> printThreadInfo(" next " + num));
        flux

                .publishOn(Schedulers.boundedElastic())
                .doOnNext(num -> printThreadInfo(" next " + num))
                .subscribe(v -> printThreadInfo(" Sub " + v));

        Util.threadSleep(1000);
    }

    public static void printThreadInfo(String msg) {
        System.out.println(msg + " Thread " + Thread.currentThread().getName());
    }
}
