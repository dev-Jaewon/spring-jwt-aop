package com.devJaewon.springSecurity.service;

import org.springframework.http.ResponseEntity;

import com.devJaewon.springSecurity.dto.LoginDto;
import com.devJaewon.springSecurity.dto.UserDto;
import com.devJaewon.springSecurity.exceptions.BadRequestException;
import com.devJaewon.springSecurity.exceptions.ConflictException;

public interface UserService {
    public void create(UserDto user) throws ConflictException;
    public ResponseEntity<String> login(LoginDto loginInfo) throws BadRequestException;
}
