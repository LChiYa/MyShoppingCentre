package com.hsd;

import com.hsd.mapper.EvaluateMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

//@SpringBootTest
class HsdGoodsServiceApplicationTests {

    @Resource
    private EvaluateMapper evaluateMapper;
    @Test
    public void contextLoads() {
//        Long count = evaluateMapper.countEvaluateInfo("1011045");
//        System.out.println("count = " + count);
    }

}
