package com.ynu.yuyue.cloud.bookservice.auth;

/**
 * Created by yuyue on 2017/9/7.
 */

import com.ynu.yuyue.cloud.bookservice.domain.User;
import com.ynu.yuyue.cloud.bookservice.mapper.UserMapper;
import com.ynu.yuyue.cloud.bookservice.security.JwtTokenUtil;
import com.ynu.yuyue.cloud.bookservice.security.JwtUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    @Qualifier("customAuthenticationManger")
    private AuthenticationManager customAuthenticationManger;

    @Autowired
    @Qualifier("jwtUserDetailService")
    private UserDetailsService jwtUserDetailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserMapper userMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;



    @Override
    public User register(User userToAdd) {
        final String username = userToAdd.getUserEmail();
        if(userMapper.findByEmail(username)!=null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getUserPassword();
        userToAdd.setUserPassword(encoder.encode(rawPassword));
        userToAdd.setLastPasswordResetDate(new Date());
        userToAdd.setRole("ROLE_USER");
        userMapper.register(userToAdd);
        return userMapper.findByEmail(userToAdd.getUserEmail());
    }

    @Override
    public String login(String username, String password) {
        Authentication upToken = new UsernamePasswordAuthenticationToken(username, password);
        // 进行安全认证
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Authentication authentication = customAuthenticationManger.authenticate(upToken);
        System.out.println("authentication");
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        System.out.println("进入登陆api3");
        // 生成token
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(username);
//        System.out.println(userDetails.getAuthorities()+userDetails.getPassword());
//        System.out.println(encoder.matches(upToken.getCredentials().toString(),userDetails.getPassword()));
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUserDetail user = (JwtUserDetail) jwtUserDetailService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
