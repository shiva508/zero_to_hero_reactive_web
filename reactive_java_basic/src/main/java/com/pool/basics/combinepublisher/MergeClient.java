package com.pool.basics.combinepublisher;

import com.pool.Util;
import com.pool.basics.combinepublisher.helper.AirIndia;
import com.pool.basics.combinepublisher.helper.Indigo;
import com.pool.basics.combinepublisher.helper.QuatorFlight;

import reactor.core.publisher.Flux;

public class MergeClient {
    public static void main(String[] args) {
        Flux<String> mergeFlux = Flux.merge(QuatorFlight.getFlights(), AirIndia.getFlights(), Indigo.getFlights());
        mergeFlux.subscribe(Util.subscriber());
    }
}
