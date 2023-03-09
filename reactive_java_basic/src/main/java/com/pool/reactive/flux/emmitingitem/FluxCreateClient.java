package com.pool.reactive.flux.emmitingitem;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class FluxCreateClient {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            String countryName = "";
            do {
                countryName = Util.fakerInstance().country().name();
                fluxSink.next(countryName);
            } while (!countryName.toLowerCase().equals("india"));
        }).subscribe(Util.subscriber());
    }

    public void fluxCreatebasic() {
        Flux.create(fluxSink -> {
            fluxSink.next("Shiva");
            fluxSink.next("Batman");
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
