package com.pool.reactive.flatmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import reactor.core.publisher.Flux;

public class OrderService {
    public static Map<Integer, List<PurchaseOrder>> map = new HashMap<>();
    static {
        List<PurchaseOrder> orderOne = Arrays.asList(new PurchaseOrder(1), new PurchaseOrder(1), new PurchaseOrder(1));
        List<PurchaseOrder> orderTwo = Arrays.asList(new PurchaseOrder(2), new PurchaseOrder(2), new PurchaseOrder(2));
        map.put(1, orderOne);
        map.put(2, orderTwo);
    }

    public static Flux<PurchaseOrder> getOrdersByUserId(Integer userId) {
        return Flux.create(emmiter -> {
            map.get(userId).forEach(emmiter::next);
            emmiter.complete();
        });
    }
}
