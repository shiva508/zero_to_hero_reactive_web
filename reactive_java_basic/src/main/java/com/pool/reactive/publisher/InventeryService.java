package com.pool.reactive.publisher;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import reactor.core.publisher.Flux;

public class InventeryService {
    public Map<String, Integer> db = new HashMap<>();

    public InventeryService() {
        db.put("Kids", 100);
        db.put("Automotive", 100);
    }

    public Consumer<PurchaseOrder> subscribeOrderStream() {
        return p -> db.computeIfPresent(p.getCategory(), (k, v) -> v - p.getQuantity());
    }

    public Flux<String> inventeryStream() {
        return Flux.interval(Duration.ofSeconds(2)).map(i -> db.toString());
    }

}
