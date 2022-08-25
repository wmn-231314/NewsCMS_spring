package com.ning.news.payload.response;

public class JWTBody {
    private String token;
    private String type = "Bearer";
    private int id;
    private String username;
    private String account;
    private String role;

    public JWTBody(String token, int id, String username, String account, String role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.account = account;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
