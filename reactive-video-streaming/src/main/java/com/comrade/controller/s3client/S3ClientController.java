package com.comrade.controller.s3client;

import com.comrade.model.S3BucketResponse;
import com.comrade.model.S3BucketDetails;
import com.comrade.model.S3FileDetails;
import com.comrade.service.s3client.S3ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/s3client")
@AllArgsConstructor
@Slf4j
public class S3ClientController {

    private final S3ClientService s3ClientService;

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
}
