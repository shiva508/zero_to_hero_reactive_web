package com.pool.reactive.flux.emmitingitem;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class FluxPushClient {
    public static void main(String[] args) {
        Flux.push(emmitor -> {
            emmitor.next("HI");
            emmitor.next("Bye");
        }).subscribe(Util.subscriber());
    }
}
