package com.pool.reactive.publisher;

import com.pool.Util;
import lombok.Data;

@Data
public class PurchaseOrder {
    private String item;
    private Double price;
    private String category;
    private Integer quantity;

    public PurchaseOrder() {

        this.item = Util.fakerInstance().company().name();
        this.price = Double.parseDouble(Util.fakerInstance().commerce().price());
        this.quantity = Util.fakerInstance().random().nextInt(1, 10);
        this.category = Util.fakerInstance().commerce().department();
    }
}
