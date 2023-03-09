package com.pool.reactive.combinepublisher;

import java.time.Duration;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class CombineClient {
    public static void main(String[] args) {
        Flux.combineLatest(heroNames(), originalNames(), (hero, ori) -> hero + " : " + ori)
                .subscribe(Util.subscriber());
        Util.threadSleep(100000);
    }

    public static Flux<String> heroNames() {
        return Flux.just("Batman", "Superman", "Aquqman", "Wonder woman").delayElements(Duration.ofSeconds(1));
    }

    public static Flux<String> originalNames() {
        return Flux.just("Bruce Wayne", "Clark Kint", "Auther", "Diana").delayElements(Duration.ofSeconds(3));
    }
}
