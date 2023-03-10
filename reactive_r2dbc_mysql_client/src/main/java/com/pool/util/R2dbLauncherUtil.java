package com.pool.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class R2dbLauncherUtil {

    @Value("classpath:queries/user_profile.sql")
    private Resource resource;

    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;

    public String getQuery(){
        try {
           return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeQuery(){
        try {
            var query = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            r2dbcEntityTemplate.getDatabaseClient()
                    .sql(query)
                    .then()
                    .subscribe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
