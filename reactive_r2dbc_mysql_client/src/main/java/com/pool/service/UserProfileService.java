package com.pool.service;

import org.springframework.core.io.Resource;

import com.pool.entity.UserProfile;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserProfileService {
	public Mono<UserProfile> save(UserProfile userProfile);

	public Mono<UserProfile> update(UserProfile userProfile);

	public Mono<Void> delete(Long userId);

	public Flux<UserProfile> all();

	public Mono<Resource> downloadFile();
}
