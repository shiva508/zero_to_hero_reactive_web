package com.pool.util;

import java.io.IOException;

public class Assignment4 {
    public static void main(String[] args) throws IOException {
        ReactiveSource.integerMono().subscribe(System.out::println);
        var number = ReactiveSource.integerMono().block();
        System.out.println(number);
        System.out.println("Press key to end");
        System.in.read();
    }
}
