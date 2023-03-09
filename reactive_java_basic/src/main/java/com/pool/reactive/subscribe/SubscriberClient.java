package com.pool.reactive.subscribe;

import java.util.function.Predicate;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class SubscriberClient {
	public static void main(String[] args) {
		Predicate<Integer> isNumberLessThan3 = num -> num <= 3;
		Flux<Integer> publisher = Flux.range(1, 10);
		publisher.filter(isNumberLessThan3).subscribe(Util.subscriber());
	}
}
