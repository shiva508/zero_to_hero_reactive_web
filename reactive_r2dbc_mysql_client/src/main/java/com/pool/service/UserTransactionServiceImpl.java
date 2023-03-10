package com.pool.service;

import com.pool.mapper.UserMapper;
import com.pool.model.TransactionModel;
import com.pool.model.TransactionResponseModel;
import com.pool.repository.UserProfileRepository;
import com.pool.repository.UserTransactionRepository;
import com.pool.util.TransactionSatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserTransactionServiceImpl implements UserTransactionService{

    private final UserProfileRepository userProfileRepository;
    private final UserTransactionRepository userTransactionRepository;
    private final UserMapper userMapper;


    @Override
    public Mono<TransactionResponseModel> createTransaction(TransactionModel transactionModel){
       return userProfileRepository.updateUserBalance(transactionModel.getUserId(),transactionModel.getAmount())
                .filter(aBoolean -> aBoolean)
                .map(aBoolean ->  userMapper.transactionModelToEntity(transactionModel))
                .flatMap(userTransactionRepository::save)
                .map(userTransaction -> userMapper.toTransactionResponseModel(transactionModel, TransactionSatus.APPROVED))
                .defaultIfEmpty(userMapper.toTransactionResponseModel(transactionModel, TransactionSatus.DECLINED));
    }
}
