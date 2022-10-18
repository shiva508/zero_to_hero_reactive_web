package com.pool.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pool.entity.UserProfile;
import com.pool.repository.UserProfileRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/userprofile")
public class UserProfileController {

	
	private final UserProfileRepository userProfileRepository;

	public UserProfileController(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}
	
	@PostMapping("/save")
	public Mono<UserProfile> save(@RequestBody UserProfile userProfile){
		return userProfileRepository.save(userProfile);
	}
}
