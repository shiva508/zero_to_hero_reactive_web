package com.pool.reactive.flux;

import java.util.concurrent.atomic.AtomicReference;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;

public class FluxSubscriptionClient {
    public static void main(String[] args) {
        AtomicReference<Subscription> atomicReference = new AtomicReference<>();
        Flux.range(0, 10).subscribeWith(new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("Received sub :" + s);
                atomicReference.set(s);
            }

            @Override
            public void onNext(Integer t) {
                System.out.println("onNext : " + t);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete: ");
            }

        });
        atomicReference.get().request(10);
    }
}
