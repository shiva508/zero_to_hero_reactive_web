package com.pool.reactive.mono;

import com.pool.Util;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class MonoSuplierRefactoringClient {
    public static void main(String[] args) {
        generateName();
        generateName();
        generateName().subscribeOn(Schedulers.boundedElastic()).subscribe(Util.onNext);
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
