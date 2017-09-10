package com.ynu.yuyue.cloud.bookservice.auth;
/**
 * Created by yuyue on 2017/9/7.
 */

import com.ynu.yuyue.cloud.bookservice.domain.User;

public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
