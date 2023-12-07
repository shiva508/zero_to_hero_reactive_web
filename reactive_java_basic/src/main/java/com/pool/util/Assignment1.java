package com.pool.util;

import java.util.Objects;

public class Assignment1 {
    public static void main(String[] args) {
        //Print all numbers
        StreamSource.intNumberStream().forEach(System.out::println);
        System.out.println("<======================================================>");
        //numbers less than 5
        StreamSource.intNumberStream().filter(integer -> integer<5).forEach(System.out::println);
        System.out.println("<======================================================>");
        //Greater than 5 & 2,3rd
        StreamSource.intNumberStream()
                    .filter(integer -> integer>5)
                    .skip(1)
                    .limit(2)
                    .forEach(System.out::println);
        System.out.println("<======================================================>");
        //find first element greater than 5
        var num = StreamSource.intNumberStream()
                              .filter(integer -> integer>5)
                              .findFirst()
                              .orElse(-1);
        System.out.println(num);
        System.out.println("<======================================================>");
        //print user firstname
        StreamSource.userStream().map(User::firstName).forEach(System.out::println);
        System.out.println("<======================================================>");
        //print user firstname where id greater than 5
        StreamSource.intNumberStream()
                    .flatMap(integer -> StreamSource.userStream()
                                                    .filter(user -> Objects.equals(user.id(), integer))
                            )
                    .map(User::firstName)
                    .forEach(System.out::println);
        System.out.println("<======================================================>");
        StreamSource.userStream()
                    .filter(user -> StreamSource.intNumberStream().anyMatch(integer -> Objects.equals(integer, user.id())))
                    .map(User::firstName).forEach(System.out::println);
    }
}
