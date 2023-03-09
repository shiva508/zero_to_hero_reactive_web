package com.pool.reactive.context;

import com.pool.Util;

public class RateLimmiterClient {
    public static void main(String[] args) {
        BookService.getBook()
                .repeat(4)
                .contextWrite(UserService.getUserContextInfo())
                .subscribe(Util.subscriber());
    }
}
