package com.pool.reactive.mono;

import java.util.function.Consumer;

import reactor.core.publisher.Mono;

public class MonoClient {
	public static void main(String[] args) {
		Mono<String> publisher = Mono.just("Batman");
		Consumer<String> consumer=System.out::println;
		publisher.subscribe(consumer);
	}
}
