package com.hsd.service;

/**
 * @ClassName GoodsInfoService
 * @Description: TODO
 * @Author:
 */
public interface GoodsInfoService {
    //减库存
    int reduceInventory(Long goodsId, Integer buyNum);
}
