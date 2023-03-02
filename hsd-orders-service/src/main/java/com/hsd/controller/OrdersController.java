package com.hsd.controller;

import com.alibaba.fastjson.JSONObject;
import com.hsd.Code;
import com.hsd.JsonResult;
import com.hsd.service.remInterface.getUserId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @ClassName OrdersController
 * @Description: TODO
 * @Author:
 */
@CrossOrigin
@RestController
public class OrdersController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private getUserId getUserId;

    /**
     * 添加订单
     * @param token   用户登录身份信息
     * @param goodsId 商品id
     * @param buyNum  购买数量
     * @return {@link Object}
     */
    @RequestMapping("/addOrders")
    public Object addOrders(String token,Long goodsId,Integer buyNum){
        Object id = getUserId.getId(token).getResult();
        return new JsonResult<Object>(Code.OK,id);
    }
}
