package com.devJaewon.springSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devJaewon.springSecurity.dto.LoginDto;
import com.devJaewon.springSecurity.dto.UserDto;
import com.devJaewon.springSecurity.entity.UserEntity;
import com.devJaewon.springSecurity.repository.UserRepository;
import com.devJaewon.springSecurity.security.JwtProvider;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(UserDto user) {
        userRepository.save(new UserEntity(user.getUserId(), passwordEncoder.encode(user.getPassword())));
    }

    public ResponseEntity<String> login(LoginDto loginInfo) {
        UserEntity result = userRepository.findByUserId(loginInfo.getUserId());

        if (result != null && passwordEncoder.matches(loginInfo.getPassword(), result.getPassword())) {
            String accessToken = new JwtProvider().createAccessToken(loginInfo.getUserId());
            return ResponseEntity.ok(accessToken);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
