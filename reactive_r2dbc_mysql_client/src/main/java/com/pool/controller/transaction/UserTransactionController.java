package com.pool.controller.transaction;

import com.pool.model.TransactionModel;
import com.pool.model.TransactionResponseModel;
import com.pool.service.UserTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class UserTransactionController {

    private final UserTransactionService userTransactionService;

    @PostMapping("/newtransaction")
    public Mono<TransactionResponseModel> createTransaction(@RequestBody Mono<TransactionModel> modelMono){
       return modelMono.flatMap(userTransactionService::createTransaction);
    }
}
