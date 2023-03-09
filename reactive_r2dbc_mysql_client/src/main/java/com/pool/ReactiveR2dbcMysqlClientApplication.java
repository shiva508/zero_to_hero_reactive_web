package com.pool;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@Log4j2
public class ReactiveR2dbcMysqlClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveR2dbcMysqlClientApplication.class,args);
    }
}