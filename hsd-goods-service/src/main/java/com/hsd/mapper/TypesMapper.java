package com.hsd.mapper;

import com.hsd.model.Types;

public interface TypesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Types record);

    int insertSelective(Types record);

    Types selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Types record);

    int updateByPrimaryKey(Types record);
}