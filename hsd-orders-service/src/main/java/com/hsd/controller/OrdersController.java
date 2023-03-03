package com.hsd.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsd.Code;
import com.hsd.JsonResult;
import com.hsd.service.OrdersService;
import com.hsd.service.remInterface.getUserId;
import com.hsd.service.remInterface.reduceInventory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    private OrdersService ordersService;

    /**
     * 添加订单
     * @param token   用户登录身份信息
     * @param goodsId 商品id
     * @param buyNum  购买数量
     * @return {@link Object}
     */
    @RequestMapping("/addOrders")
    public JsonResult<Object> addOrders(String token,Long goodsId,Integer buyNum){
        //获取登录过用户的id
        JsonResult<Long> jsonResult = getUserId.getId(token);
        //判断用户是否登录
        if (jsonResult.getCode().equals(Code.NO_LOGIN.getCode())){
            return new JsonResult<Object>(Code.NO_LOGIN,"请先登录");
        }
        //添加订单
        JsonResult<Object> jsOb = ordersService.addOrdersAndOrderDetails(jsonResult.getResult(),goodsId,buyNum);
        return new JsonResult<Object>(Code.OK,jsOb);
    }
}
