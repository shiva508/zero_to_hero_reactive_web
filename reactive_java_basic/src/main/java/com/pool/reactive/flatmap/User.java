package com.pool.reactive.flatmap;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String name;

    public User(Integer userId) {
        this.userId = userId;
    }

}
