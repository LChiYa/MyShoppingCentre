package com.hsd.service.impl;

import com.hsd.Code;
import com.hsd.JsonResult;
import com.hsd.mapper.OrderInfoMapper;
import com.hsd.mapper.OrdersMapper;
import com.hsd.model.GoodsInfo;
import com.hsd.model.OrderInfo;
import com.hsd.model.Orders;
import com.hsd.service.GoodsInfoService;
import com.hsd.service.OrdersService;
import com.hsd.service.remInterface.reduceInventory;
import org.omg.CORBA.ORB;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
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

    @Resource
    private GoodsInfoService goodsInfoService;

    @Override
    public JsonResult<Object> addOrdersAndOrderDetails(Long userId, Long goodsId, Integer buyNum) {
        //减库存并返回商品价格
        JsonResult<Object> objectJsonResult = reduceInventory.subtractStory(goodsId, buyNum);

        //根据商品id查询商品价格和库存
        List<GoodsInfo> list = goodsInfoService.selectGoodsPriceAndStore(goodsId);
        if (list == null){
            return new JsonResult<>(Code.ERROR,"库存或者价格没有查到");
        }
        //获取库存和价格
        Integer store = null;
        BigDecimal price = null;
        for (GoodsInfo goodsInfo : list) {
            store = goodsInfo.getStore();
            price = goodsInfo.getPrice();
        }

        //进入if表示没有库存
        if (objectJsonResult.getCode().equals(Code.NOT_IN_STOCK.getCode())){
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
        //将订单写入到数据库 并且返回订单id
        int rows1 = ordersMapper.insertSelective(orders);
        //进入if表示添加失败
        if (rows1 == 0){
            return new JsonResult<>(Code.ERROR,"添加订单失败");
        }
        //添加订单详情
        //创建订单详情对象
        OrderInfo orderInfo = new OrderInfo();
        //添加购买价格
        orderInfo.setPrice(price);
        //添加购买数量
        orderInfo.setAmount(buyNum);
        //添加商品id
        orderInfo.setGoodsId(goodsId);
        //添加订单id
        orderInfo.setOrdersId(orders.getId());
        //向数据库写入订单详情
        int rows2 = orderInfoMapper.insertSelective(orderInfo);
        //进入if表示写入失败
        if (rows2 == 0){
            return new JsonResult<>(Code.ERROR,"添加订单详情失败");
        }
        return new JsonResult<>(Code.OK,"订单和订单详情添加成功");
    }
}
