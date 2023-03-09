package com.pool.mapper;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.pool.entity.UserProfile;
import com.pool.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

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
}
