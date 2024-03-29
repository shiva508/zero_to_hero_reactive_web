package com.pool.basics.threading;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class PublishOnSubscribeOnClient {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadInfo(" create ");
            for (int index = 0; index < 4; index++) {
                fluxSink.next(index + 2);
            }
            fluxSink.complete();
        }).doOnNext(num -> printThreadInfo(" next " + num));
        flux

                .publishOn(Schedulers.parallel())
                .doOnNext(num -> printThreadInfo(" next " + num))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(v -> printThreadInfo(" Sub " + v));

        Util.threadSleep(1000);
    }

    public static void printThreadInfo(String msg) {
        System.out.println(msg + " Thread " + Thread.currentThread().getName());
    }
}
