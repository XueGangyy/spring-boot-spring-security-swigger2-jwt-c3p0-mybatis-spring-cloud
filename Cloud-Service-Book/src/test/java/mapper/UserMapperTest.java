package mapper;

import com.ynu.yuyue.cloud.bookservice.BookApplication;
import com.ynu.yuyue.cloud.bookservice.domain.User;
import com.ynu.yuyue.cloud.bookservice.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yuyue on 2017/9/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=BookApplication.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void registerUser(){
        User u=new User();
        u.setUserEmail("1760551922@qq.com");
        u.setUserName("余跃");
        u.setUserPassword("123456");
        Assert.assertEquals(userMapper.register(u),1);
    }

    @Test
    public void findByEmail(){
        User u=userMapper.findByEmail("1760551922@qq.com");
        Assert.assertNotNull(u);
        System.out.println(u.toString());
    }
}
