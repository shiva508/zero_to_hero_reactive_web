package com.pool.reactive.backprusure;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class DefaultBackPruseureClient {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for (int i = 0; i < 508; i++) {
                System.out.println("Publishing :" + i);
                fluxSink.next(i);
            }
            fluxSink.complete();
        })
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(num -> Util.threadSleep(10))
                .subscribe(Util.subscriber());
        Util.threadSleep(90000);
    }
}
