package com.ynu.yuyue.cloud.bookservice.security;

import com.ynu.yuyue.cloud.bookservice.domain.User;
import com.ynu.yuyue.cloud.bookservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yuyue on 2017/9/9.
 */
@Service("jwtUserDetailService")
public class JwtUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u=userMapper.findByEmail(username);//根据传上来的名字来查找用户
//        System.out.println("进入userDetailService后："+u.toString());
        if (u == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else{
            List<String> listRoles=new ArrayList<String>();
            listRoles.add(u.getRole());
            listRoles.add("ROLE_ADMIN");
//            System.out.println(listRoles.get(0));
            UserDetails jwtuser= new JwtUserDetail(u.getUserEmail(),u.getUserPassword(),mapToGrantedAuthorities(listRoles),u.getLastPasswordResetDate());
//            System.out.println(jwtuser.getUsername());
//            System.out.println("加密后的密码"+jwtuser.getPassword());
            return jwtuser;
        }
//        System.out.println(jwtuser.toString());
    }


    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
