package com.pool.reactive.publisher;

import com.pool.Util;

public class AmazonClient {

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventeryService inventeryService = new InventeryService();

        orderService.getOrderStream().subscribe(revenueService.subscribeOrderStream());
        orderService.getOrderStream().subscribe(inventeryService.subscribeOrderStream());

        inventeryService.inventeryStream().subscribe(Util.subscriber("Inventery"));
        revenueService.revenueStream().subscribe(Util.subscriber("Revenue"));
        Util.threadSleep(10000);
    }
}
