package com.ynu.yuyue.cloud.bookservice.security;

import java.io.Serializable;

/**
 * Created by yuyue on 2017/9/7.
 * @Author:简书作者（接灰的电子产品）
 */

public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
