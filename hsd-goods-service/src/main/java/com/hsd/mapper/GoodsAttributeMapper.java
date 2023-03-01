package com.hsd.mapper;

import com.hsd.model.GoodsAttribute;

public interface GoodsAttributeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsAttribute record);

    int insertSelective(GoodsAttribute record);

    GoodsAttribute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsAttribute record);

    int updateByPrimaryKey(GoodsAttribute record);
}