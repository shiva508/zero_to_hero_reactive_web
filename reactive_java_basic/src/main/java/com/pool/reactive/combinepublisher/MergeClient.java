package com.pool.reactive.combinepublisher;

import com.pool.Util;
import com.pool.reactive.combinepublisher.helper.AirIndia;
import com.pool.reactive.combinepublisher.helper.Indigo;
import com.pool.reactive.combinepublisher.helper.QuatorFlight;

import reactor.core.publisher.Flux;

public class MergeClient {
    public static void main(String[] args) {
        Flux<String> mergeFlux = Flux.merge(QuatorFlight.getFlights(), AirIndia.getFlights(), Indigo.getFlights());
        mergeFlux.subscribe(Util.subscriber());
    }
}
