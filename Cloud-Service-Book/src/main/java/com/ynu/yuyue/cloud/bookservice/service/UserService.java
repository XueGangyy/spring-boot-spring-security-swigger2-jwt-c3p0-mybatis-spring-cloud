package com.ynu.yuyue.cloud.bookservice.service;

import com.ynu.yuyue.cloud.bookservice.domain.User;

/**
 * Created by yuyue on 2017/9/7.
 */
public interface UserService {
    User register(User u);

    User login(User u);
}
