package com.pool.reactive.backprusure;

import java.util.ArrayList;
import java.util.List;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackpressureDropWithHandlerClient {

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        List<Object> droppedItems = new ArrayList<>();
        Flux.create(fluxSink -> {
            for (int index = 0; index < 508; index++) {
                System.out.println("Publish : " + index);
                fluxSink.next(index);
                Util.threadSleep(1);
            }
            fluxSink.complete();

        })
                .onBackpressureDrop(
                        droppedItems::add)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(num -> Util.threadSleep(10))
                .subscribe(Util.subscriber());
        Util.threadSleep(10000);
        System.out.println(droppedItems);

    }
}
