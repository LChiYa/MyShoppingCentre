package com.hsd;

import com.hsd.mapper.EvaluateMapper;
import com.hsd.mapper.GoodsInfoMapper;
import com.hsd.model.Evaluate;
import com.hsd.model.GoodsInfo;
import com.hsd.service.GoodsInfoService;
import com.hsd.service.GoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SpringBootTest
class HsdGoodsServiceApplicationTests {

    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private EvaluateMapper evaluateMapper;
    @Test
    public void contextLoads() {
//        Long a = evaluateMapper.countEvaluateInfo(1011045L, "A");
//        System.out.println("统计 = " + a);
        PageBean<List<Evaluate>> listPageBean = goodsService.selectEvaluateByGoodsId(1L, 5L, "", 1011045L);
        List<Evaluate> data = listPageBean.getData();
        System.out.println("data = " + data);
    }

}
