package com.pool.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommonExceptionResponse {
    private Integer errorCode;
    private String message;
}
