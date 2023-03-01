package com.hsd;

import com.hsd.mapper.UserMapper;
import com.hsd.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;

@SpringBootTest
class HsdUserServiceApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static String a = "5";

    @Test
    public void contextLoads() {
//        String a = "5";
        stringRedisTemplate.opsForValue().set("logInErr:" + 123, a);
//        stringRedisTemplate.opsForValue().setIfAbsent()
//        Long increment;
        Long increment;
        do {
//            increment = stringRedisTemplate.opsForValue().increment("logInErr:" + 123);
            increment = stringRedisTemplate.opsForValue().decrement("logInErr:" + 123);
            System.out.println("decrement = " + increment);
        }while (increment > 0);

    }



}
