package com.pool.reactive.publisher;

import java.time.Duration;
import java.util.Objects;

import reactor.core.publisher.Flux;

public class OrderService {
    private Flux<PurchaseOrder> flux;

    public Flux<PurchaseOrder> getOrderStream() {
        if (Objects.isNull(flux)) {
            flux = getPurchaseOrders();
        }
        return flux;
    }

    private static Flux<PurchaseOrder> getPurchaseOrders() {
        return Flux.interval(Duration.ofMillis(100)).map(p -> new PurchaseOrder()).publish().refCount(2);
    }
}
