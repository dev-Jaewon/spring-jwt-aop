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

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Map<String, String>> badRequestException(Exception e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> map = new HashMap<>();
        map.put("message", "아이디 및 비밀번호를 확인해주세요.");

        return new ResponseEntity<>(map, httpStatus);
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Map<String, String>> conflictException(Exception e){
              HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, httpStatus);
    }
}
