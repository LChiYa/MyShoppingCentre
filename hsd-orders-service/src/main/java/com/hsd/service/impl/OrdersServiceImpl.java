package com.hsd.service.impl;

import com.hsd.mapper.OrderInfoMapper;
import com.hsd.mapper.OrdersMapper;
import com.hsd.service.OrdersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @ClassName OrdersServiceImpl
 * @Description: TODO
 * @Author:
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public Integer addOrdersAndOrderDetails(Long result, Long goodsId, Integer buyNum, BigDecimal objectJsonResultResult) {

        return buyNum;
    }
}
