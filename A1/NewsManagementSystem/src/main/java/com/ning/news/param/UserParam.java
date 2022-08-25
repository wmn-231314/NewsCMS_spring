package com.ning.news.param;

import org.springframework.web.bind.annotation.RequestParam;

public class UserParam {
    private String account;
    private String name;
    private String password;
    private String passwordConfirm;
    private String permission;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "UserParam{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
