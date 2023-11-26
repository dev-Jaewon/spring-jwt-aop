package com.devJaewon.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devJaewon.springSecurity.dto.LoginDto;
import com.devJaewon.springSecurity.dto.UserDto;
import com.devJaewon.springSecurity.service.UserServiceImpl;

@RestController
@RequestMapping("account")
public class User {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("create")
    public void postCreate(@RequestBody UserDto user) {
        userService.create(user);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginInfo){
        return userService.login(loginInfo);
    }
}
