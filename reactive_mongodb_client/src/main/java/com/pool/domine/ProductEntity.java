package com.pool.domine;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("PRODUCT")
public class ProductEntity {
    private String id;
    private String decription;
    private Integer price;
}
