package com.pool.reactive.flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import reactor.core.publisher.Flux;

public class FluxClient {
	public static void main(String[] args) {
		Flux<String> publisher = Flux.just("Superman", "Batman", "Death Stroke");
		Consumer<String> consumer = System.out::println;
		publisher.subscribe(consumer);

		List<String> names = Arrays.asList("Hulk", "Ironman", "Antman");
		Flux<String> collectionToFluxPublisher = Flux.fromIterable(names);
		collectionToFluxPublisher.subscribe(consumer);

		List<String> words = Arrays.asList("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog");

		Flux.fromIterable(words)
				.flatMap(word -> Flux.fromArray(word.split("")))
				.distinct()
				.sort()
				.zipWith(Flux.range(1, 100), (word, cound) -> cound + ". " + word)
				.subscribe(consumer);
		Flux<String> fastClock = Flux.interval(Duration.ofMillis(1)).map(tick -> "fast " + tick);
		Flux<String> slowClock = Flux.interval(Duration.ofSeconds(2)).map(tick -> "fast" + tick);
		Flux<String> clock = Flux.merge(fastClock, slowClock);
		clock.subscribe(System.out::println);

	}
}
