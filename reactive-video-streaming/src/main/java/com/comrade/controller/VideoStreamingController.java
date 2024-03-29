package com.comrade.controller;

import com.comrade.service.VideoStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class VideoStreamingController {

    @Autowired
    private VideoStreamingService videoStreamingService;

    @GetMapping(value = "videos/{title}", produces = "videos/mp4")
    public Mono<Resource> getVideo(@PathVariable String title, @RequestHeader("Range") String range) {
        System.out.println(range);
        return videoStreamingService.initiateVideoStreaming(title);
    }
}
