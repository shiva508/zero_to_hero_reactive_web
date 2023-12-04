package com.comrade.config.s3client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import java.net.URI;

@Configuration
public class S3ClientConfiguration {

    @Bean( name = "s3ClientCredentials")
    public S3Client s3ClientCredentials(){
        return S3Client.builder()
                       .region(Region.of("us-east-1"))
                       .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("dasarishiva","dasarishiva")))
                       .build();
    }

    @Bean(name = "s3ClientEndpointBased")
    @Primary
    public S3Client s3ClientEndpointBased(){
        return S3Client.builder()
                .region(Region.of("us-east-1"))
                .endpointOverride(URI.create("http://s3.localhost.localstack.cloud:4566"))
                .build();
    }

    @Bean(name = "s3AsyncClientEndpointBased")
    public S3AsyncClient s3AsyncClientEndpointBased(){
      return S3AsyncClient.builder()
              .region(Region.of("us-east-1"))
              .endpointOverride(URI.create("http://s3.localhost.localstack.cloud:4566"))
              .build();
    }
}
