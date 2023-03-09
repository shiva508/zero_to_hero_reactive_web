package com.pool.reactive.context;

import com.pool.Util;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class ContextClient {
    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user", "Shiva Dasari"))
                .subscribe(Util.subscriber());
    }

    public static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome " + ctx.get("user"));
            } else {
                return Mono.error(new RuntimeException("Something went wrong"));
            }
        });
    }
}
