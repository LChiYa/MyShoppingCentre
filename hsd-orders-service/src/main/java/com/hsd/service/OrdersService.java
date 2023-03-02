package com.hsd.service;

import java.math.BigDecimal;

/**
 * @ClassName OrdersService
 * @Description: TODO
 * @Author:
 */
public interface OrdersService {
    Integer addOrdersAndOrderDetails(Long result, Long goodsId, Integer buyNum, BigDecimal objectJsonResultResult);

}
