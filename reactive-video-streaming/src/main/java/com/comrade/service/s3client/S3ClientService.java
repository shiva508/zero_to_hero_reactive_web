package com.comrade.service.s3client;

import com.comrade.model.S3BucketDetails;
import com.comrade.model.S3BucketResponse;
import com.comrade.model.S3FileDetails;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.core.async.ResponsePublisher;
import software.amazon.awssdk.core.async.SdkPublisher;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class S3ClientService {

    private final S3Client s3ClientEndpointBased;

    private final S3AsyncClient s3AsyncClientEndpointBased;

    public S3ClientService(@Qualifier("s3ClientEndpointBased") S3Client s3ClientEndpointBased,
                           @Qualifier("s3AsyncClientEndpointBased") S3AsyncClient s3AsyncClientEndpointBased) {
        this.s3ClientEndpointBased = s3ClientEndpointBased;
        this.s3AsyncClientEndpointBased = s3AsyncClientEndpointBased;
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

    public Flux<ByteBuffer> downloadFile(String key, String bucketName){
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                                                            .key(key)
                                                            .bucket(bucketName)
                                                            .build();
        return Mono.fromFuture(s3AsyncClientEndpointBased.getObject(getObjectRequest, AsyncResponseTransformer.toPublisher()))
                   .flatMapMany(getObjectResponseResponsePublisher -> getObjectResponseResponsePublisher);
        /*return Mono.fromFuture(s3AsyncClientEndpointBased.getObject(getObjectRequest, AsyncResponseTransformer.toPublisher()))
                   .flatMapMany(this::transformData);*/

    }

    private Publisher<? extends ByteBuffer> transformData(ResponsePublisher<GetObjectResponse> getObjectResponseResponsePublisher) {
        return getObjectResponseResponsePublisher;
    }

    public Flux<byte[]> downloadFileAsByteArray(String key, String bucketName){
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                                                            .key(key)
                                                            .bucket(bucketName)
                                                            .build();
//        downloadFileAsByteArrayNew(key,bucketName);
        return Mono.fromFuture(s3AsyncClientEndpointBased.getObject(getObjectRequest, AsyncResponseTransformer.toPublisher()))
                   .flatMapMany(this::byteBufferToByteArrayTransformer);

    }
    public void downloadFileAsByteArrayNew(String key, String bucketName){
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                                                            .key(key)
                                                            .bucket(bucketName)
                                                            .build();
         Mono.fromFuture(s3AsyncClientEndpointBased.getObject(getObjectRequest, AsyncResponseTransformer.toPublisher()))
                .subscribe(getObjectResponseResponsePublisher ->{
                    SdkPublisher<byte[]> map = getObjectResponseResponsePublisher.map(ByteBuffer::array);
                    map.subscribe(bytes -> {
                        System.out.println("======================");
                        FileOutputStream out = null;
                        try {
                            out = new FileOutputStream("/home/shiva/shiva/backend/zero_to_hero_reactive_web/reactive-video-streaming/video"+new Random().nextInt(1,1000) +".mp4");
                            System.out.println(bytes);
                            out.write(bytes);
                            out.close();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });

    }
    private Publisher<? extends byte[]> byteBufferToByteArrayTransformer(ResponsePublisher<GetObjectResponse> getObjectResponseResponsePublisher) {
        return getObjectResponseResponsePublisher.map(ByteBuffer::array);
    }

}
