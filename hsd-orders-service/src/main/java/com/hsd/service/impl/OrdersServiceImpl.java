package com.hsd.service.impl;

import com.hsd.Code;
import com.hsd.JsonResult;
import com.hsd.mapper.OrderInfoMapper;
import com.hsd.mapper.OrdersMapper;
import com.hsd.model.Orders;
import com.hsd.service.OrdersService;
import com.hsd.service.remInterface.reduceInventory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName OrdersServiceImpl
 * @Description: TODO
 * @Author:
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private reduceInventory reduceInventory;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public JsonResult<Object> addOrdersAndOrderDetails(Long userId, Long goodsId, Integer buyNum) {
        //减库存并返回商品价格
        JsonResult<Map<String,Object>> objectJsonResult = reduceInventory.subtractStory(goodsId, buyNum);
        //获取价格和库存
        Map<String, Object> map = objectJsonResult.getResult();
        //价格
        BigDecimal price = (BigDecimal) map.get("price");
        //库存
        Integer store = (Integer) map.get("store");
        //进入if表示没有库存
        if (objectJsonResult.getResult() == null){
            return new JsonResult<Object>(Code.NOT_IN_STOCK,"库存还有" + store + "个");
        }
        //创建订单对象
        Orders orders = new Orders();
        //添加订单金额
        orders.setOrdersMoney(price);
        //添加订单实付金额
        orders.setActualMoney(price);
        //添加积分抵扣金额
        orders.setPoint(0);
        //添加订单状态
        orders.setStatus(1);
        //添加订单创建时间
        orders.setCreateTime(System.currentTimeMillis());
        //添加用户ID
        orders.setUserId(userId);
        //添加收货地址Id 外键
        orders.setAddressId(1L);
        //添加订单编号
        orders.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        //将订单写入到数据库
        int rows1 = ordersMapper.insertSelective(orders);
        //进入if表示添加失败
        if (rows1 == 0){
            return new JsonResult<>(Code.ERROR,"添加订单失败");
        }
        //添加订单详情
        return new JsonResult<>(Code.OK,objectJsonResult.getResult());
    }
}
