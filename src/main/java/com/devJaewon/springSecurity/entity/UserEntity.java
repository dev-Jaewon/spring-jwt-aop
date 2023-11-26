package com.devJaewon.springSecurity.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "account")
@Table(name = "account")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Nonnull
    private String userId;

    @Nonnull
    private String password;

    @Nonnull
    private String role;

    public UserEntity() {}

    public UserEntity(String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.role = "USER";
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    };
}
