package com.pool.reactive.mono.assignment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import reactor.core.publisher.Mono;

public class FileOperations {
    public static Path PATH = Paths.get("/home/shiva/shiva/mywork/zero_to_hero_reactive_java/src/main/resources");

    public static Mono<String> monoReadData(String filename) {
        return Mono.fromSupplier(() -> readDataFromFile(filename));
    }

    public static Mono<Void> monoWriteData(String filename, String content) {
        return Mono.fromRunnable(() -> writeDataToFile(filename, content));
    }

    public static Mono<Void> monoDeleteFile(String filename) {
        return Mono.fromRunnable(() -> deleteFile(filename));
    }

    public static String readDataFromFile(String filename) {
        try {
            return new String(Files.readAllBytes(PATH.resolve(filename)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeDataToFile(String filename, String content) {
        try {
            Files.writeString(PATH.resolve(filename), content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String filename) {
        try {
            Files.delete(PATH.resolve(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
