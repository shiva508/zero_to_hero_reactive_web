package com.pool.reactive.combinepublisher.helper;

import java.util.ArrayList;
import java.util.List;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class NameGenerator {

    private List<String> names = new ArrayList<>();

    public Flux<String> generateName() {
        return Flux.generate(syncFluxSink -> {
            System.out.println("Generate fresh");
            Util.threadSleep(1000);
            String name = Util.fakerInstance().name().firstName();
            names.add(name);
            syncFluxSink.next(name);
        })
                .cast(String.class)
                .startWith(getFromCached());
    }

    public Flux<String> getFromCached() {
        return Flux.fromIterable(names);
    }
}
