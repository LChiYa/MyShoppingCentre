package com.hsd.mapper;

import com.hsd.model.Evaluate;

import java.util.List;

public interface EvaluateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKeyWithBLOBs(Evaluate record);

    int updateByPrimaryKey(Evaluate record);

    Long countEvaluateInfo(String goodsId, String evaluationLevel);

    List<Evaluate> findAllProductReviewsByGoodsId(Long skipNum, Long pageSize, String evaluationLevel, String goodsId);
}