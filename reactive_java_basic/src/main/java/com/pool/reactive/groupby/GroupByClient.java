package com.pool.reactive.groupby;

import java.time.Duration;

import com.pool.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;

public class GroupByClient {
    public static void main(String[] args) {
        Flux.range(0, 49).delayElements(Duration.ofMillis(100))
                .groupBy(num -> num % 2)
                .subscribe(numGroup -> processNumber(numGroup, numGroup.key()));
        Util.threadSleep(10000);
    }

    private static void processNumber(GroupedFlux<Integer, Integer> numGroup, Integer key) {
        numGroup.subscribe(num -> System.out.println(key + ":" + num));
    }

}
