package com.pool.reactive.batching.assignone;

import com.github.javafaker.Book;
import com.pool.Util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookOrder {
    private String title;
    private String auther;
    private String category;
    private double price;

    public BookOrder() {
        Book book = Util.fakerInstance().book();
        this.title = book.title();
        this.auther = book.author();
        this.category = book.genre();
        this.price = Double.parseDouble(Util.fakerInstance().commerce().price());

    }
}
