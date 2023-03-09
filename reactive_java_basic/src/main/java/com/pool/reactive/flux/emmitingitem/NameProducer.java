package com.pool.reactive.flux.emmitingitem;

import java.util.function.Consumer;

import com.pool.Util;

import reactor.core.publisher.FluxSink;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> t) {
        fluxSink = t;
    }

    public void produce() {
        String name = Util.fakerInstance().country().name();
        var threadName = Thread.currentThread().getName();
        fluxSink.next(threadName + ":" + name);
    }
}
