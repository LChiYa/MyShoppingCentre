package com.hsd.service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName GoodsInfoService
 * @Description: TODO
 * @Author:
 */
public interface GoodsInfoService {
    //减库存
    int reduceInventory(Long goodsId, Integer buyNum);

    Map<String, Object> selectGoodsPriceAndStore(Long goodsId);
}
