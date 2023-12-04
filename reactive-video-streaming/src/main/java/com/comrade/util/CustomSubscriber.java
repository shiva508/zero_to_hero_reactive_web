package com.comrade.util;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class CustomSubscriber implements Subscriber<Object> {

    String name;

    public CustomSubscriber(String name) {
        this.name = name;
    }

    public CustomSubscriber() {

    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);

    }

    @Override
    public void onNext(Object t) {
        if (null != this.name) {
            System.out.println(name + ": received: " + t);
        } else {
            System.out.println(" received: " + t);
        }

    }

    @Override
    public void onError(Throwable t) {
        System.out.println(" Error: " + t.getMessage());

    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }

}

