package com.pool.reactive.context;

import java.util.Map;
import java.util.function.Function;

import reactor.util.context.Context;

public class UserService {
    private static final Map<String, String> MAP = Map.of("Shiva", "user", "Batman", "admin");

    public static Function<Context, Context> getUserContextInfo() {
        return ctx -> {
            String user = ctx.get("user").toString();
            String category = MAP.get(user);
            return ctx.put("category", category);
        };
    }
}
