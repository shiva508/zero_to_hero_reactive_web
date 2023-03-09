package com.pool.reactive.flatmap;

import com.pool.Util;

import lombok.Data;

@Data
public class PurchaseOrder {
    private String item;
    private String price;
    private Integer userId;

    public PurchaseOrder(Integer userId) {
        this.userId = userId;
        this.item = Util.fakerInstance().company().name();
        this.price = Util.fakerInstance().commerce().price();
    }

}
