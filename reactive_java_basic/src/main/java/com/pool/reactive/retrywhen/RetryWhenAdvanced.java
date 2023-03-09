package com.pool.reactive.retrywhen;

import java.time.Duration;

import com.pool.Util;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class RetryWhenAdvanced {
    public static void main(String[] args) {
        orderService(Util.fakerInstance().business().creditCardNumber())
                .doOnEach(err -> System.out.println(err))
                .retryWhen(Retry.from(flux -> flux.doOnNext(rs -> {
                    System.out.println("totalRetries:" + rs.totalRetries());
                    System.out.println("failure:" + rs.failure());
                }).handle((rs, synchronouseSink) -> {
                    if (rs.failure().getMessage().equals("500")) {
                        synchronouseSink.next(1);
                    } else if (rs.failure().getMessage().equals("400")) {
                        synchronouseSink.error(rs.failure());
                    }
                }).delayElements(Duration.ofMillis(100)))).subscribe(Util.subscriber());

        Util.threadSleep(10000);
    }

    public static Mono<String> orderService(String ccNmber) {
        return Mono.fromSupplier(() -> {
            processPayment(ccNmber);
            return Util.fakerInstance().idNumber().valid();
        });
    }

    public static void processPayment(String ccNmber) {
        int random = Util.fakerInstance().random().nextInt(1, 10);
        if (random < 8) {
            throw new RuntimeException("500");
        } else if (random < 10) {
            throw new RuntimeException("400");
        }
    }
}
