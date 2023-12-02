package com.comrade.service.s3client;

import com.comrade.model.S3BucketDetails;
import com.comrade.model.S3BucketResponse;
import com.comrade.model.S3FileDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class S3ClientService {

    private final S3Client s3ClientEndpointBased;

    public S3ClientService(@Qualifier("s3ClientEndpointBased") S3Client s3ClientEndpointBased) {
        this.s3ClientEndpointBased = s3ClientEndpointBased;
    }

    public S3BucketResponse createBucket(String bucketName){
        CreateBucketRequest createBucketRequest = CreateBucketRequest.builder()
                                                                     .bucket(bucketName)
                                                                     .build();
        CreateBucketResponse createBucketResponse = s3ClientEndpointBased.createBucket(createBucketRequest);
        return S3BucketResponse.builder()
                               .location(createBucketResponse.location())
                               .successful(createBucketResponse.sdkHttpResponse().isSuccessful())
                               .message(createBucketResponse.sdkHttpResponse().statusText().get()).build();
    }

    public void uploadFile(MultipartFile multipartFile){
       var filename = multipartFile.getResource().getFilename();
       var contentType = multipartFile.getContentType();
       log.info("File Name = {}",filename);
       log.info("contentType = {}",contentType);
       try {
           PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                                               .bucket("bucket-shiva")
                                                               .key(filename)
                                                               .acl("public-read")
                                                               .contentType(contentType)
                                                               .bucketKeyEnabled(true)
                                                               .build();
           s3ClientEndpointBased.putObject(putObjectRequest, RequestBody.fromInputStream(multipartFile.getInputStream(),multipartFile.getInputStream().available()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<S3BucketDetails> getS3Buckets(){

      var buckets =  s3ClientEndpointBased.listBuckets()
                                          .buckets()
                                          .stream()
                                          .map(bucket -> S3BucketDetails.builder().name(bucket.name()).creationDate(bucket.creationDate().toString()).build())
                                          .toList();
      log.info("AWS buckets size {} ",buckets.size());
      return buckets;
    }

    public List<S3FileDetails> awsListOfObjects(String bucketName){
        ListObjectsRequest listObjects = ListObjectsRequest.builder()
                                                           .bucket(bucketName)
                                                           .build();
        ListObjectsResponse listObjectsResponse = s3ClientEndpointBased.listObjects(listObjects);
        List<S3Object> s3Objects = listObjectsResponse.contents();
        return s3Objects.stream().map(s3Object -> S3FileDetails.builder().name(s3Object.key()).size(s3Object.size()).owner(s3Object.owner().displayName()).build()).toList();
    }

}
