package com.ynu.yuyue.cloud.bookservice.security;

/**
 * Created by yuyue on 2017/9/9.
 */
public class JwtAuthenticationRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
