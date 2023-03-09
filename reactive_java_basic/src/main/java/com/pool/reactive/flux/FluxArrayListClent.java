package com.pool.reactive.flux;

import java.util.Arrays;
import java.util.List;
import com.pool.Util;
import reactor.core.publisher.Flux;

public class FluxArrayListClent {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4);
        Flux.fromIterable(intList).subscribe(Util.onNext);
        Integer[] intArray = { 1, 2, 3, 4 };
        Flux.fromArray(intArray).subscribe(Util.onNext);
    }
}
