package com.pool.reactive.transform;

import java.util.function.Consumer;
import java.util.function.Function;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class PersonTransformClient {
    public static void main(String[] args) {
        getPersons().transform(upperCaseTransform()).subscribe(Util.subscriber());
    }

    public static Flux<Person> getPersons() {
        return Flux.range(0, 8).map(num -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> upperCaseTransform() {
        return personFlux -> personFlux.filter(person -> person.getAge() < 50)
                .doOnNext(personsNameConsumer())
                .doOnDiscard(Person.class, ga -> System.out.println(" Noy " + ga));
    }

    public static Consumer<Person> personsNameConsumer() {
        return gsg -> gsg.setFirstName(gsg.getFirstName().toUpperCase());
    }
}
