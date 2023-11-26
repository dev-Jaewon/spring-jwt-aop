package com.devJaewon.springSecurity.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;

@Entity(name = "account")
@Table(name = "account")
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Nonnull
    @Column(name = "name")
    private String userId;

    @Nonnull
    @Column(name = "password")
    private String password;

    @Nonnull
    @ManyToAny(fetch = FetchType.LAZY)
    @JoinTable(name = "account_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> role = new ArrayList<RoleEntity>();

    public UserEntity() {
    }

    public UserEntity(String userId, String password, List<RoleEntity> role) {
        this.userId = userId;
        this.password = password;
        this.role.addAll(role);
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

    public List<RoleEntity> getRole() {
        return this.role;
    }

    public void setRole(List<RoleEntity> role) {
        this.role = role;
    }

}
