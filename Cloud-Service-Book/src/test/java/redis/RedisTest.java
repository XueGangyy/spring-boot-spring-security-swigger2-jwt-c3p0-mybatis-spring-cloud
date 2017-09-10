package redis;

import com.ynu.yuyue.cloud.bookservice.BookApplication;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yuyue on 2017/9/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=BookApplication.class)
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() throws Exception {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        stringRedisTemplate.delete("aaa");
        String s=stringRedisTemplate.opsForValue().get("aaa");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }
}
