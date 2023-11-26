package com.devJaewon.springSecurity.service;

import org.springframework.http.ResponseEntity;

import com.devJaewon.springSecurity.dto.LoginDto;
import com.devJaewon.springSecurity.dto.UserDto;

public interface UserService {
    public void create(UserDto user);
    public ResponseEntity<String> login(LoginDto loginInfo);
}
