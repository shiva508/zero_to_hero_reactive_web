package com.pool.controller.user;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pool.entity.UserProfile;
import com.pool.repository.UserProfileRepository;
import com.pool.service.UserProfileService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/userprofile")
public class UserProfileController {

	private final UserProfileRepository userProfileRepository;

	private final UserProfileService profileService;

	public UserProfileController(UserProfileRepository userProfileRepository, UserProfileService profileService) {
		this.userProfileRepository = userProfileRepository;
		this.profileService = profileService;
	}

	@PostMapping("/save")
	public Mono<UserProfile> save(@RequestBody UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}

	@GetMapping("/downloadFile")
	public ResponseEntity<Mono<Resource>> downloadFile() throws IOException {

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(
						"application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=appl.zip")
				.body(profileService.downloadFile());
	}
}
