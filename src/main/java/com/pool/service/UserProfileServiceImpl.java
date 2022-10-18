package com.pool.service;

import org.springframework.stereotype.Service;

import com.pool.entity.UserProfile;
import com.pool.repository.UserProfileRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
	
	private final UserProfileRepository userProfileRepository;

	@Override
	public Mono<UserProfile> save(UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}

	@Override
	public Mono<UserProfile> update(UserProfile userProfile) {
		
		return userProfileRepository.save(userProfile);
	}

	@Override
	public Mono<Void> delete(Long userId) {
		return userProfileRepository.deleteById(userId);
	}

	@Override
	public Flux<UserProfile> all() {
		return userProfileRepository.findAll();
	}

}
