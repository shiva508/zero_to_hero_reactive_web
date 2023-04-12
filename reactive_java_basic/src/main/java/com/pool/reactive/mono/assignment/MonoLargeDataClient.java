package com.pool.reactive.mono.assignment;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonoLargeDataClient {
    public static void main(String[] args) throws InterruptedException {

        Mono.fromRunnable(MonoLargeDataClient::mailCallerMethod).subscribeOn(Schedulers.boundedElastic()).subscribe();
        Thread.sleep(10000);
        ExecutorService executorService= Executors.newFixedThreadPool(4);
    }

    private static void mailCallerMethod() {
        Mono.fromRunnable(MonoLargeDataClient::timeTakingTask).subscribeOn(Schedulers.boundedElastic()).subscribe();
        Mono.fromRunnable(MonoLargeDataClient::quickTask).subscribeOn(Schedulers.boundedElastic()).subscribe();

    }

    public static void timeTakingTask(){
        try {
            Thread.sleep(10000);
            System.out.println("timeTakingTask=======================>");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void quickTask(){
        System.out.println("quickTask=======================>");
    }
}
