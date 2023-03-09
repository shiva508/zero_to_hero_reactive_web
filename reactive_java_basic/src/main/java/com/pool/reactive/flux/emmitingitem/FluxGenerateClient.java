package com.pool.reactive.flux.emmitingitem;

import com.pool.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateClient {
    public static void main(String[] args) {
        Flux.generate(synchronoseSink -> {
            String country = Util.fakerInstance().country().name();
            System.out.println("emmiting:" + country);
            synchronoseSink.next(country);
            if (country.toLowerCase().equals("canada")) {
                synchronoseSink.complete();
            }
        }).subscribe(Util.subscriber());
    }
}
