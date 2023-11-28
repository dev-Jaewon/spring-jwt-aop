package com.devJaewon.springSecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devJaewon.springSecurity.dto.LoginDto;
import com.devJaewon.springSecurity.dto.UserDto;
import com.devJaewon.springSecurity.entity.RoleEntity;
import com.devJaewon.springSecurity.entity.UserEntity;
import com.devJaewon.springSecurity.exceptions.BadRequestException;
import com.devJaewon.springSecurity.exceptions.ConflictException;
import com.devJaewon.springSecurity.repository.RoleRepository;
import com.devJaewon.springSecurity.repository.UserRepository;
import com.devJaewon.springSecurity.security.JwtProvider;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public void create(UserDto user) throws ConflictException {
        UserEntity checkUserId = userRepository.findByUserId(user.getUserId());

        if(checkUserId != null){
            throw new ConflictException("userId가 중복입니다.");
        }

        RoleEntity role = roleRepository.findByName("USER");
        String password = passwordEncoder.encode(user.getPassword());

        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles.add(role);

        userRepository.save(new UserEntity(user.getUserId(), password, roles));
    }

    public ResponseEntity<String> login(LoginDto loginInfo) throws BadRequestException {
            UserEntity result = userRepository.findByUserId(loginInfo.getUserId());

            if (result != null && passwordEncoder.matches(loginInfo.getPassword(), result.getPassword())) {
                String accessToken = new JwtProvider().createAccessToken(loginInfo.getUserId());
                return ResponseEntity.ok(accessToken);
            } else {
                throw new BadRequestException();
            }

    }
}
