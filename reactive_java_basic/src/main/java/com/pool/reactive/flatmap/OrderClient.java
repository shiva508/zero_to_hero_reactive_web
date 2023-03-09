package com.pool.reactive.flatmap;

import com.pool.Util;

public class OrderClient {
    public static void main(String[] args) {
        UserService.users().flatMap(user -> OrderService.getOrdersByUserId(user.getUserId()))
                .subscribe(Util.subscriber());
    }
}
