package com.pool.basics.threading;

import com.pool.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnClient {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadInfo(" create ");
            fluxSink.next(408);
        })
                .subscribeOn(Schedulers.newParallel(" Parallel "))
                .doOnNext(num -> printThreadInfo(" next " + num));
        Runnable runnable = () -> flux
                .doFirst(() -> printThreadInfo(" first2 "))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadInfo(" first1 "))
                .subscribe(v -> printThreadInfo(" Sub " + v));

        for (int index = 0; index < 2; index++) {
            new Thread(runnable).start();
        }
        Util.threadSleep(1000);
    }

    public static void printThreadInfo(String msg) {
        System.out.println(msg + " Thread " + Thread.currentThread().getName());
    }
}
