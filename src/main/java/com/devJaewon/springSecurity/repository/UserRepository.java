package com.devJaewon.springSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devJaewon.springSecurity.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>  {
    UserEntity findByUserId(String userId);
}
