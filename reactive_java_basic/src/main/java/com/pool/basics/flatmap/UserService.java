package com.pool.basics.flatmap;

import reactor.core.publisher.Flux;

public class UserService {
    public static Flux<User> users() {
        return Flux.create(emmit -> {
            emmit.next(new User(1));
            emmit.next(new User(2));
        });
    }
}
