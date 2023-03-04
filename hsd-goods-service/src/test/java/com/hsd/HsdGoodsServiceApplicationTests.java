package com.hsd;

import com.hsd.mapper.EvaluateMapper;
import com.hsd.mapper.GoodsInfoMapper;
import com.hsd.model.GoodsInfo;
import com.hsd.service.GoodsInfoService;
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
    @Test
    public void contextLoads() {
//        BigDecimal bigDecimal = goodsInfoMapper.selectGoodsPrice(1011045L);
//        Integer integer = goodsInfoMapper.selectGoodsStore(1011045L);
//        System.out.println("bigDecimal = " + bigDecimal);
//        System.out.println("integer = " + integer);
//        Map<String, Object> map = goodsInfoService.selectGoodsPriceAndStore(1011045L);
//        BigDecimal price = (BigDecimal) map.get("price");
//        Integer store = (Integer) map.get("store");
//        System.out.println("price = " + price);
//        List<GoodsInfo> list = goodsInfoMapper.selectGoodsPrice(1011045L);
//        for (GoodsInfo goodsInfo : list) {
////            System.out.println("goodsInfo = " + goodsInfo);
//            goodsInfo.get
//        }
//        System.out.println("store = " + store);
    }

}
