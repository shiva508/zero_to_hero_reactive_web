package com.pool.util;

import com.pool.basics.subscriber.CustomSubscriber;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Assignment5 {
    public static void main(String[] args) throws IOException {
        ReactiveSource.integerMono().subscribe(num-> System.out.println(num),
                                               err-> System.out.println(err),
                                               ()-> System.out.println("Completed"));
        System.out.println("<=========================>");
//        ReactiveSource.integerFluxWithException().subscribe(num-> System.out.println(num),
//                                                            err-> System.out.println(err),
//                                                            ()-> System.out.println("Completed"));
        System.out.println("<=========================>");
        ReactiveSource.intNumberFlux().subscribe(new CustomSubscriber<>());
        System.out.println("Press key to end");
        System.in.read();

    }
}
