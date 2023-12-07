package com.pool.util;

import java.io.IOException;

public class Assignment3 {
    public static void main(String[] args) throws IOException {
        //ReactiveSource.intNumberFlux().subscribe(System.out::println);
        var list =  ReactiveSource.intNumberFlux().toStream().toList();
        System.out.println(list);
        System.out.println("Press key to end");
        System.in.read();
    }
}
