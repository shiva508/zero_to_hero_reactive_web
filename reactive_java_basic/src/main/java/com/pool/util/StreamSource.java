package com.pool.util;

import java.util.stream.Stream;

public class StreamSource {

    public static Stream<String> stringNumbersStream(){
        return Stream.of("one","two","three","four","five","six","seven","eight","nine","ten");
    }

    public static Stream<Integer> intNumberStream(){
        return Stream.iterate(0,num->num+2).limit(10);
    }


    public static Stream<User> userStream(){
        return Stream.of(new User(1,"Teena","Jhones"),
                new User(1,"Martha","Wyane"),
                new User(2,"Batman","Bruce"),
                new User(3,"Banner","Bruce"),
                new User(4,"Agent","Closen"),
                new User(5,"Captan","America"),
                new User(6,"Deadshot","Shooter"),
                new User(7,"Krish","Krishna murithi"),
                new User(8,"Doctor","Strange"),
                new User(9,"Thore","Ragnarok"),
                new User(10,"Star","Lord"));
    }
}
