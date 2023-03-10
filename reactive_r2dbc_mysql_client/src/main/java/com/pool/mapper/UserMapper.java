package com.pool.mapper;

import com.pool.entity.UserProfile;
import com.pool.entity.UserTransaction;
import com.pool.model.TransactionModel;
import com.pool.model.TransactionResponseModel;
import com.pool.model.UserModel;
import com.pool.util.TransactionSatus;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class UserMapper {
    public UserModel userEntityToModel(UserProfile userProfile){
        UserModel userModel=new UserModel();
        BeanUtils.copyProperties(userProfile,userModel);
        return userModel;
    }

    public UserProfile userModelToEntity(UserModel userModel){
        UserProfile userProfile=new UserProfile();
        BeanUtils.copyProperties(userModel,userProfile);
        return userProfile;
    }

    public UserTransaction transactionModelToEntity(TransactionModel transactionModel){
        UserTransaction userTransaction=new UserTransaction();
        userTransaction.setUserId(transactionModel.getUserId());
        userTransaction.setAmount(transactionModel.getAmount());
        userTransaction.setTransactionTime(LocalDateTime.now());
        return userTransaction;
    }

    public TransactionResponseModel toTransactionResponseModel(TransactionModel transactionModel,
                                                               TransactionSatus satus){
        TransactionResponseModel transactionResponseModel=new TransactionResponseModel();
        transactionResponseModel.setUserId(transactionModel.getUserId());
        transactionResponseModel.setAmount(transactionModel.getAmount());
        transactionResponseModel.setSatus(satus);
        return transactionResponseModel;
    }
}
