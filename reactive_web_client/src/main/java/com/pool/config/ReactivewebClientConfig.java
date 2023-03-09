package com.pool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ReactivewebClientConfig {

    @Bean
    public WebClient reactiveWebClient(){
        return WebClient
                .builder()
                .baseUrl("http://localhost:8092")
                .build();
    }

}
