package com.hsd.mapper;

import com.hsd.model.Goods;
import com.hsd.model.GoodsInfo;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    int reduceInventory(Long goodsId, Integer buyNum);

    List<GoodsInfo> selectGoodsPriceAndStore(Long goodsId);

}