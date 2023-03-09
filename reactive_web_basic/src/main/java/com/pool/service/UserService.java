package com.pool.service;

import com.pool.entity.UserEntity;
import com.pool.util.UserUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class UserService {

    public static List<UserEntity>userEntities=new ArrayList<>();
    static {
        userEntities.add(new UserEntity("A"));
        userEntities.add(new UserEntity("B"));
        userEntities.add(new UserEntity("C"));
        userEntities.add(new UserEntity("D"));
        userEntities.add(new UserEntity("E"));
        userEntities.add(new UserEntity("F"));
        userEntities.add(new UserEntity("G"));
        userEntities.add(new UserEntity("H"));
        userEntities.add(new UserEntity("I"));
        userEntities.add(new UserEntity("J"));
        userEntities.add(new UserEntity("K"));
        userEntities.add(new UserEntity("L"));
        userEntities.add(new UserEntity("M"));
        userEntities.add(new UserEntity("N"));
    }

    public UserEntity getUserByName(String name){
      return userEntities.stream()
              .filter(userEntity -> userEntity.getName().equals(name))
              .findFirst()
              .orElseGet(()->new UserEntity("No User found with search"));
    }

    public List<UserEntity> getAllUsers(){
        return userEntities.stream().peek(userEntity -> {
            UserUtil.sleep(1);
            userEntity.setName(userEntity.getName()+new Random().nextInt());
        }).collect(Collectors.toList());
    }
}
