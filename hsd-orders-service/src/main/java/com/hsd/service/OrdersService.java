package com.hsd.service;

import com.hsd.JsonResult;

import java.math.BigDecimal;

/**
 * @ClassName OrdersService
 * @Description: TODO
 * @Author:
 */
public interface OrdersService {
    JsonResult<Object> addOrdersAndOrderDetails(Long result, Long goodsId, Integer buyNum);

}
