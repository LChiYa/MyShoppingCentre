package com.hsd.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsd.Code;
import com.hsd.JsonResult;
import com.hsd.service.remInterface.getUserId;
import com.hsd.service.remInterface.reduceInventory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Array;
import java.time.Duration;
import java.util.ArrayList;

/**
 * @ClassName OrdersController
 * @Description: TODO
 * @Author:
 */
@CrossOrigin
@RestController
public class OrdersController {

    @Resource
    private getUserId getUserId;
    @Resource
    private reduceInventory reduceInventory;

    /**
     * 添加订单
     * @param token   用户登录身份信息
     * @param goodsId 商品id
     * @param buyNum  购买数量
     * @return {@link Object}
     */
    @RequestMapping("/addOrders")
    public Object addOrders(String token,Long goodsId,Integer buyNum){
        //获取登录过用户的id
        Object id = getUserId.getId(token).getResult();
        //减库存
        JsonResult<Object> jsonResult = reduceInventory.subtractStory(goodsId, buyNum);
//        Object result = jsonResult.getResult();
        if (jsonResult == null){
            return new JsonResult<Object>(Code.OK,"库存不足");
        }
        //添加订单
        //添加订单详情
        return new JsonResult<Object>(Code.OK,id);
    }
}
