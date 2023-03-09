package com.pool.reactive.threading;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class ThreadClient {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadInfo("create");
            fluxSink.next(408);
        }).doOnNext(num -> printThreadInfo("next"));
        Runnable runnable = () -> flux.subscribe(v -> printThreadInfo("Sub " + v));
        for (int index = 0; index < 2; index++) {
            new Thread(runnable).start();
        }
        Util.threadSleep(1000);
    }

    public static void printThreadInfo(String msg) {
        System.out.println(msg + "\t\t Thread " + Thread.currentThread().getName());
    }
}
