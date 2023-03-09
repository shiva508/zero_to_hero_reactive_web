package com.pool.reactive.context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.pool.Util;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class BookService {
    private static Map<String, Integer> map = new HashMap<>();
    static {
        map.put("user", 2);
        map.put("admin", 3);
    }

    public static Mono<String> getBook() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("allow")) {
                return Mono.just(Util.fakerInstance().book().title());
            } else {
                return Mono.error(new RuntimeException("Not Allowed"));
            }
        }).contextWrite(rateLimmiterContext());
    }

    public static Function<Context, Context> rateLimmiterContext() {
        return ctx -> {
            if (ctx.hasKey("category")) {
                String category = ctx.get("category").toString();
                Integer attempts = map.get(category);
                if (attempts > 0) {
                    map.put(category, attempts - 1);
                    return ctx.put("allow", true);
                }
            }
            return ctx.put("allow", false);
        };
    }
}
