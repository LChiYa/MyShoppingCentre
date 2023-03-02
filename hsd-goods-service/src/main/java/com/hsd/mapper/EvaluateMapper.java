package com.hsd.mapper;

import com.hsd.model.Evaluate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface EvaluateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    Evaluate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKeyWithBLOBs(Evaluate record);

    int updateByPrimaryKey(Evaluate record);

    Long countEvaluateInfo(Long goodsId, String evaluationLevel);

    List<Evaluate> findAllProductReviewsByGoodsId(Long skipNum, Long pageSize, String evaluationLevel, Long goodsId);

    Long countEvaluateInfoImg(Long goodsId);

    List<Evaluate> findAllProductReviewsByGoodsIdImg(Long skipNum, Long pageSize, Long goodsId);

    LinkedHashMap<String,String> countEvaluateNum(Long goodsId);
}