package com.hsd.mapper;

import com.hsd.model.GoodsInfo;

import java.math.BigDecimal;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    int reduceInventory(Long goodsId, Integer buyNum);

    BigDecimal selectGoodsPrice(Long goodsId);
}