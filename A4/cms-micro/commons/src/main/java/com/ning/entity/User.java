package com.ning.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ning.payload.request.RegisterRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

public class User {
    private int id;
    private String account;
    private String password;
    private String name;
    private String permission;
    private String token;
    private Date registerTime;
    private Date lastLoginTime;
    private int banned;

    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User(RegisterRequest registerRequest){
        if(registerRequest.getAccount() != null) this.account = registerRequest.getAccount();
        if(registerRequest.getPassword() != null) this.password = registerRequest.getPassword();
        if(registerRequest.getName() != null) this.name = registerRequest.getName();
        if(registerRequest.getPermission() != null) this.permission = registerRequest.getPermission();
    }

    public User(int id, String account, String password, String name, String permission, String token,
                Date registerTime, Date lastLoginTime, int banned) {
        this.id = id;
        this.account = account;
        this.password = password;
        try {
            this.name = name == null ? null : URLDecoder.decode(name, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.permission = permission;
        this.token = token;
        this.registerTime = registerTime;
        this.lastLoginTime = lastLoginTime;
        this.banned = banned;
    }

    public User(String account, String password, String name, String permission, String token, Date registerTime, Date lastLoginTime) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.permission = permission;
        this.token = token;
        this.registerTime = registerTime;
        this.lastLoginTime = lastLoginTime;
    }

    public User(String account, String password, String name) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.banned = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", acount='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", permission='" + permission + '\'' +
                ", token='" + token + '\'' +
                ", register_time=" + registerTime +
                ", last_login_time=" + lastLoginTime +
                ", banned=" + banned +
                '}';
    }
}
