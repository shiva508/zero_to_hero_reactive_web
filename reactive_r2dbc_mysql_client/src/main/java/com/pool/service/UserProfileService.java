package com.pool.service;

import com.pool.model.UserModel;
import org.springframework.core.io.Resource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserProfileService {
	public Mono<UserModel> save(Mono<UserModel> userModel);

	public Mono<UserModel> update(Long userId,Mono<UserModel> userModel);

	public Mono<Void> delete(Long userId);

	public Flux<UserModel> all();

	public Mono<UserModel> findByUserId(Long userId);
	public Mono<Resource> downloadFile();
}
