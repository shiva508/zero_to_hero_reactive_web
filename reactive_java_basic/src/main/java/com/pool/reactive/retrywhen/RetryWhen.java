package com.pool.reactive.retrywhen;

import java.time.Duration;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

public class RetryWhen {

    public static void main(String[] args) {
        numberStream()
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3)))
                .subscribe(Util.subscriber());
    }

    public static Flux<Integer> numberStream() {
        return Flux.range(0, 9)
                .doOnSubscribe(num -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("**Completed"))
                .map(num -> num / (Util.fakerInstance().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(err -> System.out.println("Error Occcured"));
    }
}
