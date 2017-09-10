package com.ynu.yuyue.cloud.bookservice.mapper;

import com.ynu.yuyue.cloud.bookservice.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by yuyue on 2017/9/7.
 */
public interface UserMapper {
    @Insert("INSERT INTO user_baseinf(user_email,user_name,user_password," +
            "user_telephone,user_address,role,lastPasswordResetDate)VALUES" +
            "(#{userEmail},#{userName},#{userPassword},#{userTelephone},#" +
            "{userAddress},#{role},#{lastPasswordResetDate})")
    int register(User u);

    @Select("SELECT u.user_email userEmail" +
            " ,u.user_name userName" +
            ",u.user_telephone userTelephone" +
            ",u.user_address userAddress" +
            ",u.user_good userGood" +
            ",u.user_trust userTrust,u.role role,user_password userPassword FROM user_baseinf as u WHERE u.user_email=#{email}")
    User findByEmail(String email);

    @Select("SELECT u.user_email userEmail" +
            " ,u.user_name userName" +
            ",u.user_telephone userTelephone" +
            ",u.user_address userAddress" +
            ",u.user_good userGood" +
            ",u.user_trust userTrust,u.role role,user_password userPassword FROM user_baseinf as u WHERE u.user_email=#{userEmail} AND u.user_password=#{userPassword}")
     User login(User u);
}
