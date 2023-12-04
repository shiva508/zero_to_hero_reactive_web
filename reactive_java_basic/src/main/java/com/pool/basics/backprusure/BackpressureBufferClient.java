package com.pool.basics.backprusure;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackpressureBufferClient {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for (int index = 0; index < 508; index++) {
                System.out.println("Publish : " + index);
                fluxSink.next(index);
                Util.threadSleep(1);

            }
            fluxSink.complete();

        })
                .onBackpressureBuffer()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(num -> Util.threadSleep(1000))
                .subscribe(Util.subscriber());
        Util.threadSleep(10000);

    }
}
