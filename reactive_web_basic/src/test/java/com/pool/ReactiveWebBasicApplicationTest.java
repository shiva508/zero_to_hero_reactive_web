package com.pool;

import com.pool.entity.UserEntity;
import com.pool.model.CommonExceptionResponse;
import com.pool.model.Input;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class ReactiveWebBasicApplicationTest extends ReactiveWebBuilder{
    @Autowired
    private WebClient reactiveWebClient;

    @Test
    public void monoData(){
        var userEntityMono = reactiveWebClient.get().uri("/reapi/byname/{name}", "A")
                .retrieve()
                .bodyToMono(UserEntity.class)
                .block();
        System.out.println(userEntityMono);
    }

    @Test
    public void stepVerifyMonoData(){
        var userEntityMono = reactiveWebClient.get().uri("/reapi/byname/{name}", "A")
                .retrieve()
                .bodyToMono(UserEntity.class);
        StepVerifier.create(userEntityMono)
                .expectNextMatches(userEntity ->userEntity.getName().equals("A")).verifyComplete();
    }

    @Test
    public void stepVerifyFluxData(){
        Flux<UserEntity> userEntityFlux = reactiveWebClient.get().uri("/reapi/all")
                .retrieve()
                .bodyToFlux(UserEntity.class);
        StepVerifier.create(userEntityFlux).expectNextCount(14).verifyComplete();
    }

    @Test
    public void stepVerifyFluxStreamData(){
        Flux<UserEntity> userEntityFlux = reactiveWebClient.get().uri("/reapi/reall")
                .retrieve()
                .bodyToFlux(UserEntity.class);
        StepVerifier.create(userEntityFlux).expectNextCount(14).verifyComplete();
    }

    @Test
    public void stepVerifyCreateUserData(){
        Mono<UserEntity> userEntity = reactiveWebClient.post().uri("/reapi/createUser")
                .bodyValue(new Input("Shiva Dasari"))
                .retrieve()
                .bodyToMono(UserEntity.class)
                .doOnNext(System.out::println);
        StepVerifier.create(userEntity)
                .expectNextMatches(userEntityop -> userEntityop.getName().equals("Shiva Dasari"))
                .verifyComplete();
    }

    @Test
    public void stepVerifyCreateUserHeadersData(){
        Mono<UserEntity> userEntity = reactiveWebClient.post().uri("/reapi/createUser")
                .bodyValue(new Input("Shiva Dasari"))
                .headers(httpHeaders -> httpHeaders.set("Batman","Bruce Wyne"))
                .retrieve()
                .bodyToMono(UserEntity.class)
                .doOnNext(System.out::println);
        StepVerifier.create(userEntity)
                .expectNextMatches(userEntityop -> userEntityop.getName().equals("Shiva Dasari"))
                .verifyComplete();
    }

    @Test
    public void stepVerifyCreateUserBadInputData(){
        Mono<UserEntity> userEntity = reactiveWebClient.post().uri("/reapi/createUser")
                .bodyValue(new Input("Sh"))
                .retrieve()
                .bodyToMono(UserEntity.class)
                .doOnNext(System.out::println)
                .doOnError( throwable -> System.out.println(throwable.getMessage()));
        StepVerifier.create(userEntity)
                .verifyError(WebClientResponseException.BadRequest.class);

    }

    @Test
    public void stepVerifyCreateUserBadInputWithExchangeData(){
        Mono<Object> userEntity = reactiveWebClient.post().uri("/reapi/createUser")
                .bodyValue(new Input("Sh"))
                .exchangeToMono(this::exchangeExtractor)
                .doOnNext(System.out::println)
                .doOnError( throwable -> System.out.println(throwable.getMessage()));
        StepVerifier.create(userEntity)
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    public void monoWithQueryParamData(){
        var userEntityMono = reactiveWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/reapi/search").queryParam("name","A").build())
                .retrieve()
                .bodyToMono(UserEntity.class)
                .block();
        System.out.println(userEntityMono);
    }

    @Test
    public void stepVerifyCreateUserHeadersOauthData(){
        Mono<UserEntity> userEntity = reactiveWebClient.post().uri("/reapi/createUser")
                .bodyValue(new Input("Shiva Dasari"))
                .headers(httpHeaders -> httpHeaders.setBasicAuth("Shiva","Shiva"))
                .retrieve()
                .bodyToMono(UserEntity.class)
                .doOnNext(System.out::println);
        StepVerifier.create(userEntity)
                .expectNextMatches(userEntityop -> userEntityop.getName().equals("Shiva Dasari"))
                .verifyComplete();
    }
    public Mono<Object> exchangeExtractor(ClientResponse clientResponse){
        if(clientResponse.statusCode().equals(HttpStatusCode.valueOf(400))){
            return clientResponse.bodyToMono(UserEntity.class);
        }else{
            return clientResponse.bodyToMono(CommonExceptionResponse.class);
        }
    }
}
