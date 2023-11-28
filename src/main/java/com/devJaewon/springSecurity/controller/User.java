package com.devJaewon.springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devJaewon.springSecurity.aop.CheckAuth;
import com.devJaewon.springSecurity.dto.LoginDto;
import com.devJaewon.springSecurity.dto.UserDto;
import com.devJaewon.springSecurity.exceptions.BadRequestException;
import com.devJaewon.springSecurity.exceptions.ConflictException;
import com.devJaewon.springSecurity.exceptions.UnAuthorizedException;
import com.devJaewon.springSecurity.service.UserService;

@RestController
@RequestMapping("account")
public class User {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public void postCreate(@RequestBody UserDto user) throws ConflictException {
        userService.create(user);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginInfo) throws BadRequestException{
        return userService.login(loginInfo);
    }

    @CheckAuth
    @GetMapping("test")
    public void test() throws UnAuthorizedException{
           System.out.println("접근완료");
    }
}
