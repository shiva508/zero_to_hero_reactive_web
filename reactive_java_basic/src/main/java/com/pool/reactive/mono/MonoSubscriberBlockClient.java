package com.pool.reactive.mono;

import com.pool.Util;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class MonoSubscriberBlockClient {
    public static void main(String[] args) {
        generateName();
        generateName();
        String name = generateName().subscribeOn(Schedulers.boundedElastic()).block();
        System.out.println(name);
        generateName();
    }

    public static Mono<String> generateName() {
        System.out.println("Entered method...");
        return Mono.fromSupplier(() -> {
            System.out.println("Genrating name.....");
            Util.threadSleep(3);
            return Util.fakerInstance().name().firstName();
        }).map(String::toUpperCase);

    }
}
