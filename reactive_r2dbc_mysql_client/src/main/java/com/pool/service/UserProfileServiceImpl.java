package com.pool.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.pool.mapper.UserMapper;
import com.pool.model.UserModel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.pool.repository.UserProfileRepository;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

	private final UserProfileRepository userProfileRepository;

	private final UserMapper userMapper;

	@Override
	public Mono<UserModel> save(Mono<UserModel> userModel) {

		return userModel.map(userMapper::userModelToEntity)
				.flatMap(userProfileRepository::save)
				.map(userMapper::userEntityToModel);
	}

	@Override
	public Mono<UserModel> update(Long userId,Mono<UserModel> userModel) {

		return userProfileRepository.findById(userId)
				.flatMap(userProfile -> userModel.map(userMapper::userModelToEntity)
						.doOnNext(userProfile1 -> userProfile1.setUserId(userId)))
				.flatMap(userProfileRepository::save)
				.map(userMapper::userEntityToModel);
	}

	@Override
	public Mono<Void> delete(Long userId) {
		return userProfileRepository.deleteById(userId);
	}

	@Override
	public Flux<UserModel> all() {

		return userProfileRepository.findAll().map(userMapper::userEntityToModel);
	}

	@Override
	public Mono<UserModel> findByUserId(Long userId) {
		return userProfileRepository.findById(userId).map(userMapper::userEntityToModel);
	}

	@Override
	public Mono<Resource> downloadFile() {
		Path path = Paths.get(
				"/home/shiva/shiva/mywork/zero_to_hero_reactive_web/src/main/resources/application.yml");
		byte[] arr;
		ByteArrayOutputStream arrayOutputStream = null;
		Resource resource = null;
		try {
			arr = Files.readAllBytes(path);
			arrayOutputStream = new ByteArrayOutputStream();
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
			resource = new InputStreamResource(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Mono.just(resource);
	}

}
