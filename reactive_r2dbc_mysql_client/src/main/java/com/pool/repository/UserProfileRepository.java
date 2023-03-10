package com.pool.repository;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.pool.entity.UserProfile;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserProfileRepository extends ReactiveCrudRepository<UserProfile, Long> {
    @Modifying
    @Query("UPDATE tbl_user_profile " +
            "SET BALANCE= BALANCE - :amount " +
            "where USER_ID= :userId AND BALANCE>= :amount "
    )
    public Mono<Boolean> updateUserBalance(Long userId,Integer amount);

}
