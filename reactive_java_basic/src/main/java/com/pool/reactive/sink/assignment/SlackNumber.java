package com.pool.reactive.sink.assignment;

import java.util.function.Consumer;

public class SlackNumber {
    private String name;
    private Consumer<String> consumeMessage;

    public SlackNumber(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void receves(String message) {
        System.out.println(message);
    }

    public Consumer<String> getConsumeMessage() {
        return consumeMessage;
    }

    public void setConsumeMessage(Consumer<String> consumeMessage) {
        this.consumeMessage = consumeMessage;
    }

    public void sendMsg(String mesage) {
        consumeMessage.accept(mesage);
    }

}
