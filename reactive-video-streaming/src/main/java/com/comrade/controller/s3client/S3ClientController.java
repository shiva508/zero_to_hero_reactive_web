package com.comrade.controller.s3client;

import com.comrade.model.S3BucketResponse;
import com.comrade.model.S3BucketDetails;
import com.comrade.model.S3FileDetails;
import com.comrade.service.TranscoderService;
import com.comrade.service.s3client.S3ClientService;
import com.comrade.util.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.nio.ByteBuffer;
import java.util.List;

@RestController
@RequestMapping("/s3client")
@AllArgsConstructor
@Slf4j
public class S3ClientController {

    private final S3ClientService s3ClientService;

    private final TranscoderService transcoderService;

    @GetMapping("/createBucket/{bucketName}")
    public S3BucketResponse createBucket(@PathVariable("bucketName") String bucketName){
        log.info("bucket name :{}",bucketName);
        return s3ClientService.createBucket(bucketName);
    }

    @PostMapping(value = "/upload",consumes = {"multipart/form-data","videos/mp4"})
    public void uploadFile(@RequestParam("file") MultipartFile file){
        s3ClientService.uploadFile(file);
    }

    @GetMapping("/buckets")
    public List<S3BucketDetails> getS3Buckets(){
       return s3ClientService.getS3Buckets();
    }

    @GetMapping("/getDetailsByBucket/{bucketName}")
    public List<S3FileDetails> awsListOfObjects(@PathVariable("bucketName") String bucketName){
       return s3ClientService.awsListOfObjects(bucketName);
    }

    @GetMapping(value = "/downloadfile/{key}/{bucketName}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ByteBuffer> downloadFile(@PathVariable("key") String key, @PathVariable("bucketName") String bucketName){
        return s3ClientService.downloadFile(key,bucketName);
    }

    @GetMapping(value = "/downloadFileByteArray/{key}/{bucketName}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<byte[]> downloadFileAsByteArray(@PathVariable("key") String key, @PathVariable("bucketName") String bucketName){
        return s3ClientService.downloadFileAsByteArray(key,bucketName);
    }

    @GetMapping(value = "/fluxStream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> getFluxString(){
       return Flux.create(fluxSink -> {
                   for (int index = 0; index < 508; index++) {
                       System.out.println("Publish : " + index);
                       fluxSink.next(index);
                       Util.threadSleep(1);
                   }
                   fluxSink.complete();
               })
               .onBackpressureBuffer()
               .publishOn(Schedulers.boundedElastic())
               .doOnNext(num -> Util.threadSleep(1000));
    }

    @GetMapping("/split")
    public void  split(){
        transcoderService.split();
    }

}
