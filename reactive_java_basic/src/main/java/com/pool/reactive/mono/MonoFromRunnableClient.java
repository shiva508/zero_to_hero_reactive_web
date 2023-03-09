package com.pool.reactive.mono;

import com.pool.Util;

import reactor.core.publisher.Mono;

public class MonoFromRunnableClient {
    public static void main(String[] args) {
        Mono.fromRunnable(timeConsumingName()).subscribe(Util.onNext, Util.onError, () -> {
            System.out.println("Sending confirmation Email");
        });
    }

    public static Runnable timeConsumingName() {
        return () -> {
            Util.threadSleep(8);
            System.out.println("Data is written to database");
        };
    }
}
