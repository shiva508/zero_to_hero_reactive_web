package com.pool.reactive.switchonfirst;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.reactivestreams.Publisher;

import com.pool.Util;
import com.pool.reactive.transform.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Signal;

public class SwitchOnFirstClient {
    public static void main(String[] args) {
        getPersons().switchOnFirst(biFunctionTran()).subscribe(Util.subscriber());
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

    public static BiFunction<Signal<? extends Person>, Flux<Person>, Publisher<? extends Person>> biFunctionTran() {
        return (signal, fluxPerson) -> signal.isOnNext() && signal.get().getAge() > 10 ? fluxPerson
                : upperCaseTransform().apply(fluxPerson);
    }
}
