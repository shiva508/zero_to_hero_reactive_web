package com.pool.reactive.doon;

import java.util.ArrayList;

import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Signal;
import reactor.core.publisher.SignalType;

public class DoOnClient {
    public static void main(String[] args) {
        var signals = new ArrayList<Signal<Integer>>();
        var nextValues = new ArrayList<Integer>();
        var subscriptions = new ArrayList<Subscription>();
        var exceptions = new ArrayList<Throwable>();
        var finallySignals = new ArrayList<SignalType>();
        Flux.<Integer>create(sink -> {
            sink.next(1);
            sink.next(2);
            sink.next(3);
            // sink.error(new IllegalArgumentException("oops!"));
            sink.complete();
        })
                .doFirst(() -> System.out.println("Process started"))
                .doOnComplete(() -> System.out.println("Everithing shoud recevied by now"))
                .doOnRequest(ks -> System.out.println("doOnREquest :"))
                .doOnNext(nextValues::add)
                .doOnEach(signals::add)
                .doOnSubscribe(subscriptions::add)
                .doOnError(IllegalArgumentException.class, exceptions::add)
                .doFinally(finallySignals::add)
                .doOnTerminate(() -> System.out.println("Terminated"))
                .doOnDiscard(Object.class, dis -> System.out.println("OnDiscord: " + dis))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .subscribe(System.out::println);
        System.out.println("signals:");
        signals.forEach(System.out::println);
    }
}
