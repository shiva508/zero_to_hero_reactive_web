package com.pool.controller.user;

import java.io.IOException;
import com.pool.model.UserModel;
import com.pool.util.R2dbLauncherUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pool.service.UserProfileService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/userprofile")
public class UserProfileController {

	private final UserProfileService profileService;

	private final R2dbLauncherUtil r2dbLauncherUtil;

	public UserProfileController(UserProfileService profileService,R2dbLauncherUtil r2dbLauncherUtil) {
		this.profileService = profileService;
		this.r2dbLauncherUtil=r2dbLauncherUtil;
	}

	@GetMapping("/all")
	public Flux<UserModel> getAllUsers(){
		return profileService.all();
	}
	@PostMapping("/save")
	public Mono<UserModel> save(@RequestBody Mono<UserModel> userModel) {
		return profileService.save(userModel);
	}
	@PutMapping("update/{userId}")
	public Mono<ResponseEntity<UserModel>> update(@PathVariable("userId") Long userId,@RequestBody Mono<UserModel> userModel){
		return profileService.update(userId,userModel).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/byuserid/{userId}")
	public Mono<ResponseEntity<UserModel>> findByUserId(@PathVariable("userId") Long userId){
		return profileService.findByUserId(userId).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/downloadFile")
	public ResponseEntity<Mono<Resource>> downloadFile() throws IOException {
		profileService.downloadFile().subscribe(data -> System.out.println(data), error -> System.out.println(error),
				() -> System.out
						.println("processing completed"));
						profileService.downloadFile().filter(null);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(
						"application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=appl.zip")
				.body(profileService.downloadFile());
	}

	@DeleteMapping("/delete/{userId}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable("userId") Long userId){
		return profileService.delete(userId).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/getquery")
	public String getquery(){
		return r2dbLauncherUtil.getQuery();
	}
}
