package com.comrade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class S3BucketResponse {
    private String message;
    private String location;
    private Boolean successful;

}
