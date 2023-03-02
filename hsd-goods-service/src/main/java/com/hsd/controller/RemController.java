package com.hsd.controller;

import com.hsd.Code;
import com.hsd.JsonResult;
import com.hsd.service.GoodsInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @ClassName RemController
 * @Description: TODO
 * @Author:
 */
@RestController
public class RemController {

    @Resource
    private GoodsInfoService goodsInfoService;

    /**
     * 减少库存
     * @param goodsId 商品id
     * @param buyNum  购买数量
     * @return {@link Object} 减少库存成功/失败
     */
    @GetMapping("/reduceInventory")
    public JsonResult<Object> reduceInventory(Long goodsId,Integer buyNum){
        //减库存
        int res = goodsInfoService.reduceInventory(goodsId,buyNum);
        //进入if表示库存不足 并返回空
        if (res == 0){
            return new JsonResult<>(Code.NOT_IN_STOCK,null);
        }
        //查询商品价格
        BigDecimal price = goodsInfoService.selectGoodsPrice(goodsId);
//        System.out.println("goodsId = " + goodsId);
//        System.out.println("buyNum = " + buyNum);
        return new JsonResult<>(Code.OK,price);
    }

}
