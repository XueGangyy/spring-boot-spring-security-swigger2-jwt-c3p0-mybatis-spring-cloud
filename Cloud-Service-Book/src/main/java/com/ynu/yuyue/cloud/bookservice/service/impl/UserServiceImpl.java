package com.ynu.yuyue.cloud.bookservice.service.impl;

import com.ynu.yuyue.cloud.bookservice.domain.User;
import com.ynu.yuyue.cloud.bookservice.mapper.UserMapper;
import com.ynu.yuyue.cloud.bookservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yuyue on 2017/9/7.
 */
@Service
@Transactional(rollbackFor=RuntimeException.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(User u) {
        int i=userMapper.register(u);
        if(i>=1){
            return userMapper.findByEmail(u.getUserEmail());
        }else{
            return null;
        }
    }

    @Override
    public User login(User u) {
        return userMapper.login(u);
    }
}
