package com.pool.reactive.batching.assignone;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.pool.Util;

import reactor.core.publisher.Flux;

public class BookClient {

    public static void main(String[] args) {
        Set<String> favorateRecify = Set.of("Science fiction", "Mystery");

        getBookOrder()
                .buffer(Duration.ofSeconds(5))
                .map(BookClient::revenueReportData)
                .subscribe(Util.subscriber());

        /*
         * getBookOrder()
         * .buffer(Duration.ofSeconds(5))
         * .map(books -> books.stream()
         * .collect(Collectors.groupingBy(BookOrder::getCategory, Collectors.toList())))
         * .subscribe(Util.subscriber());
         * 
         */
        Util.threadSleep(10000);
    }

    public static RevenueReport revenueReportData(List<BookOrder> booksOrder) {
        Map<String, Double> data = booksOrder.stream()
                .collect(Collectors.groupingBy(BookOrder::getCategory, Collectors.summingDouble(BookOrder::getPrice)));
        return new RevenueReport(data);
    }

    public static Flux<BookOrder> getBookOrder() {
        return Flux.interval(Duration.ofMillis(200)).map(book -> new BookOrder());
    }
}
