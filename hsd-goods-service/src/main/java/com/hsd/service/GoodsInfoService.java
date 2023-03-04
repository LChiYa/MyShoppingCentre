package com.hsd.service;

import com.hsd.model.GoodsInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GoodsInfoService
 * @Description: TODO
 * @Author:
 */
public interface GoodsInfoService {
    //减库存
    int reduceInventory(Long goodsId, Integer buyNum);

    List<GoodsInfo> selectGoodsPriceAndStore(Long goodsId);
}
