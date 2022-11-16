package com.pool.controller.user;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
	public Mono<UserProfile> save(@RequestBody UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}

	@GetMapping("/downloadFile")
	public ResponseEntity<Mono<Resource>> downloadFile() throws IOException {
		// Load file as Resource

		Path path = Paths.get(
				"/home/shiva/shiva/mywork/zero_to_hero_reactive_web/src/main/resources/application.yml");
		byte[] arr = Files.readAllBytes(path);
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		arrayOutputStream.write(arr);

		ByteArrayOutputStream arrayOutputStream2 = new ByteArrayOutputStream();
		ZipOutputStream zipOutputStream = new ZipOutputStream(arrayOutputStream2);
		ZipEntry zipEntry = new ZipEntry("shiva.yml");
		zipEntry.setSize(arrayOutputStream.toByteArray().length);
		zipOutputStream.putNextEntry(zipEntry);
		zipOutputStream.write(arrayOutputStream.toByteArray());
		zipOutputStream.closeEntry();
		zipOutputStream.close();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(arrayOutputStream2.toByteArray());
		Resource resource = new InputStreamResource(inputStream);

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(
						"application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=appl.zip")
				.body(Mono.just(resource));
	}
}
