package com.pool.util;

import java.io.IOException;
import java.util.Objects;

public class Assignment2 {
    public static void main(String[] args) throws IOException {
        //ReactiveSource.intNumberFlux().subscribe(System.out::println);
        ReactiveSource.userFlux().subscribe(System.out::println);
        System.out.println("Press key to end");
        System.in.read();
    }
}
