package com.pool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
public class ReactivewebClientConfig {

    @Bean
    public WebClient reactiveWebClient(){
        return WebClient
                .builder()
                .baseUrl("http://localhost:8092")
                .filter(this::oauthAdditer)
                .build();
    }



    public Mono<ClientResponse> oauthAdditer(ClientRequest clientRequest, ExchangeFunction exchangeFunction){
        ClientRequest clientRequestObj = ClientRequest.from(clientRequest).headers(httpHeaders -> httpHeaders.setBearerAuth("ShivaToken")).build();
        return exchangeFunction.exchange(clientRequestObj);
    }

}
