package com.pool.basics.batching.assignone;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.ToString;

@ToString
public class RevenueReport {
    private LocalDateTime localDateTime = LocalDateTime.now();
    private Map<String, Double> revenue;

    public RevenueReport(Map<String, Double> revenue) {
        this.revenue = revenue;
    }

}
