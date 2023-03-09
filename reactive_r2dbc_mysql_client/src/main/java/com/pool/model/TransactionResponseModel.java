package com.pool.model;

import com.pool.util.TransactionSatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseModel {
    private Long userId;
    private Integer amount;
    private TransactionSatus satus;
}
