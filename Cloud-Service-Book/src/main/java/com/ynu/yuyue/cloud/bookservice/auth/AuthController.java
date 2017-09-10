package com.ynu.yuyue.cloud.bookservice.auth;

/**
 * Created by yuyue on 2017/9/7.
 */

import com.ynu.yuyue.cloud.bookservice.domain.User;
import com.ynu.yuyue.cloud.bookservice.mapper.UserMapper;
import com.ynu.yuyue.cloud.bookservice.security.JwtAuthenticationRequest;
import com.ynu.yuyue.cloud.bookservice.security.JwtAuthenticationResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value="登陆api")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Auth> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException{
        String oldToken=stringRedisTemplate.opsForValue().get(authenticationRequest.getUsername());
        System.out.println("缓存里面的token"+oldToken);
        if(oldToken==null) {
            String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            stringRedisTemplate.opsForValue().set(authenticationRequest.getUsername(),token);
            User user = userMapper.findByEmail(authenticationRequest.getUsername());
            return new ResponseEntity<>(new Auth(token,user),HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ApiOperation(value="属性token")
    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
    }

    @ApiOperation(value="用户注册api")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<User>  register(@RequestBody User user) throws AuthenticationException{
        return  Optional.ofNullable(authService.register(user))
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseThrow(() -> new RuntimeException("error.register.fail"));
    }

}
