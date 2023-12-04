package com.comrade.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class VideoStreamingService {
    @Value("${video.path}")
    private String videoPath;

    @Autowired
    private ResourceLoader resourceLoader;

    public Mono<Resource> initiateVideoStreaming(String title) {
        return Mono.fromSupplier(() -> this.resourceLoader.getResource(String.format(videoPath, title)));
    }
}
