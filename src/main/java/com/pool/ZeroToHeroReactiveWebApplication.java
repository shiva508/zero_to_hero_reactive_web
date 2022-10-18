package com.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import com.pool.entity.UserProfile;
import com.pool.repository.UserProfileRepository;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableR2dbcRepositories
@Log4j2
public class ZeroToHeroReactiveWebApplication {

	@Autowired
	private UserProfileRepository userProfileRepository;

	public static void main(String[] args) {
		SpringApplication.run(ZeroToHeroReactiveWebApplication.class, args);
	}

	@EventListener(value = { ApplicationReadyEvent.class })
	public void go() {
		var names=Flux.just("Shiva","Dasari","Ravi","Satish")
				.map(name->UserProfile.builder().userId(null).firstName(name).build())
				.flatMap(userProfileRepository::save)
				.thenMany(userProfileRepository.findAll())
				.subscribe(log::info);
		
		
		/*
		 * userProfileRepository.deleteAll() .thenMany(names)
		 * .thenMany(userProfileRepository.findAll()) .subscribe(log::info);
		 */
		
	}

}
