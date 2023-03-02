package com.hsd;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hsd.mapper.UserMapper;
import com.hsd.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;

//@SpringBootTest
class HsdUserServiceApplicationTests {


    @Test
    public void contextLoads() {

        String where =  " and head." + "query" + " like '%" +  "queryV" + "%'";
        String sort = "1,2,3,4,5,6,7,8";
        String[] s = sort.split(",");
        for (int i = 0; i < s.length; i++) {
            String a = s[i].replace(","," ");
            if(i != 0) {
                where = where + " order by head." + a;
            }else {
                where += "," + a;
            }
        }
        System.out.println("where = " + where);
    }



}
