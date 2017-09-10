package com.ynu.yuyue.cloud.bookservice.security;

import com.ynu.yuyue.cloud.bookservice.domain.User;
import com.ynu.yuyue.cloud.bookservice.mapper.UserMapper;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by yuyue on 2017/9/9.
 */
@Component("customAuthenticationManger")
public class CustomAuthenticationManger implements AuthenticationManager {
    @Autowired
    private UserDetailsService jwtUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        System.out.println("进入manager");
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();//获取验证信息
        UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);
//        System.out.println("用户名"+authentication.getName());
//        System.out.println(userDetails.getPassword());
        if(userDetails!=null){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            Boolean b=encoder.matches(password,userDetails.getPassword());
            System.out.println(b);
            if(b){
                Authentication auth = new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
                return auth;
            }else{
                throw new BadCredentialsException("密码错误~");
            }
        }else{
            throw new BadCredentialsException("用户名不存在");
        }
    }

}
