package com.ynu.yuyue.cloud.bookservice.auth;

/**
 * Created by yuyue on 2017/9/7.
 */
import com.ynu.yuyue.cloud.bookservice.domain.User;

public class Auth {
    private String token;
    private User user;

    public Auth(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
