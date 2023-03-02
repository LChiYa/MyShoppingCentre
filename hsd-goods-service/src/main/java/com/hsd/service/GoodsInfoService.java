package com.hsd.service;

import java.math.BigDecimal;

/**
 * @ClassName GoodsInfoService
 * @Description: TODO
 * @Author:
 */
public interface GoodsInfoService {
    //减库存
    int reduceInventory(Long goodsId, Integer buyNum);

    BigDecimal selectGoodsPrice(Long goodsId);

}
