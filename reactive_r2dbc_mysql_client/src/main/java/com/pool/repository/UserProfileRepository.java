package com.pool.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.pool.entity.UserProfile;

public interface UserProfileRepository extends ReactiveCrudRepository<UserProfile, Long> {

}
