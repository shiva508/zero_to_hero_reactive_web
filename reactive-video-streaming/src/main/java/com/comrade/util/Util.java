package com.comrade.util;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Util {
    public static final Faker FAKER = Faker.instance();
    public static Consumer<Object> onNext = obj -> System.out.println("Recevied:" + obj);
    public static Consumer<Throwable> onError = err -> System.out.println("Error:" + err.getMessage());
    public static Runnable onComplete = () -> System.out.println("Completed");

    public static Faker fakerInstance() {
        return FAKER;
    }

    public static void threadSleep(int milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> nameGenerator(int count) {
        List<String> names = new ArrayList<>();
        for (int index = 0; index < count; index++) {
            names.add(name());
        }
        return names;
    }

    public static Flux<String> nameGeneratorreactive(int count) {

        return Flux.range(0, count).map(t -> name());
    }

    public static String name() {
        threadSleep(1000);
        return fakerInstance().name().firstName();
    }

    public static Subscriber<Object> subscriber() {
        return new CustomSubscriber();
    }

    public static Subscriber<Object> subscriber(String name) {
        return new CustomSubscriber(name);
    }
}
