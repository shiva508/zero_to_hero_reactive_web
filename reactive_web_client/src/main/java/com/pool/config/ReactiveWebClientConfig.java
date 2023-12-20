package com.pool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ReactiveWebClientConfig {

    @Bean("reactiveWebClient")
    public WebClient reactiveWebClient(){
        return WebClient
                .builder()
                .baseUrl("http://localhost:8092")
                .build();
    }

    //https://docs.github.com/en/rest/using-the-rest-api/getting-started-with-the-rest-api?apiVersion=2022-11-28
    @Bean("gitWebClient")
    public WebClient gitWebClient(){
        return WebClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.github.v3+json")
                .defaultHeader(HttpHeaders.USER_AGENT,"zero_to_hero_reactive_web")
                .defaultHeader(HttpHeaders.ACCEPT,"application/vnd.github+json")
                .build();
    }
}
