package com.zephon.controller.test;

import com.zephon.domain.UserInfo;
import com.zephon.utils.JwtUtil;
import com.zephon.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zephon
 * @version V1.0
 * @Package com.zephon.controller.test
 * @date 2020/3/22 下午7:39
 * @Copyright ©
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-redis.xml"})
public class JwtTest {
    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void test(){
        UserInfo user = new UserInfo();
        user.setAvatar("alksdfjlkjasglasdf");
        redisUtil.set("123",user);
        Object o = redisUtil.get("123");
        System.out.println(o);
    }
}
