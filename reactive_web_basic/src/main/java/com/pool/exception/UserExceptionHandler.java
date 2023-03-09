package com.pool.exception;

import com.pool.model.CommonExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<CommonExceptionResponse> userException(UserException userException){
        return new ResponseEntity<>(CommonExceptionResponse.builder()
                .errorCode(500)
                .message(userException.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }
}
