package com.devJaewon.springSecurity.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalApiErrorHandler {

    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseEntity<Map<String, String>> unAuthorizedException(Exception e) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        Map<String, String> map = new HashMap<>();
        map.put("message", "토큰이 만료되었습니다.");

        return new ResponseEntity<>(map, httpStatus);
    }

}
