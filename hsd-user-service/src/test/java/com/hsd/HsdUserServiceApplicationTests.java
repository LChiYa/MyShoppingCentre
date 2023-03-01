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

//    private static long a;
//    static {
//        a = 5;
//    }
    @Test
    public void contextLoads() {
//        Duration duration = Duration.ofSeconds(1);
//        stringRedisTemplate.opsForValue().set("logInErr:" + 123,a,duration);
//        stringRedisTemplate.opsForValue().setIfAbsent()
//        Long decrement = stringRedisTemplate.opsForValue().decrement("logInErr:" + 123);
        for (int a = 5; a >= 1; a--) {
            if (a-- < a) {
                System.out.println(a);
            }
        }
//        System.out.println("a = " + a);
    }



}
