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
    private reduceInventory reduceInventory;

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
    public Object addOrders(String token,Long goodsId,Integer buyNum){
        //获取登录过用户的id
        JsonResult<Long> jsonResult = getUserId.getId(token);
        //判断用户是否登录
        if (jsonResult.getCode().equals(Code.NO_LOGIN.getCode())){
            return new JsonResult<Object>(Code.NO_LOGIN,"请先登录");
        }
        //减库存并返回商品价格
        JsonResult<BigDecimal> objectJsonResult = reduceInventory.subtractStory(goodsId, buyNum);
//        Object result = jsonResult.getResult();
        if (objectJsonResult.getResult() == null){
            return new JsonResult<Object>(Code.NOT_IN_STOCK,"");
        }
        //添加订单
        Integer i = ordersService.addOrdersAndOrderDetails(jsonResult.getResult(),goodsId,buyNum,objectJsonResult.getResult());
        return new JsonResult<BigDecimal>(Code.OK,objectJsonResult.getResult());
    }
}
