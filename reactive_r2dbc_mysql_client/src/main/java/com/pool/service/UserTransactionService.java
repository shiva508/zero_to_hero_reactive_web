package com.pool.service;

import com.pool.model.TransactionModel;
import com.pool.model.TransactionResponseModel;
import reactor.core.publisher.Mono;

public interface UserTransactionService {
    public Mono<TransactionResponseModel> createTransaction(TransactionModel transactionModel);
}
