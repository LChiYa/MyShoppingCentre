package com.hsd.service;

import com.hsd.PageBean;
import com.hsd.model.Evaluate;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName GoodsService
 * @Description: TODO
 * @Author:
 */
public interface GoodsService {
    PageBean<List<Evaluate>> selectEvaluateByGoodsId(Long pageNo, Long pageSize, String evaluationLevel, Long goodsId);

    LinkedHashMap<String,String> countEvaluateNum(Long goodsId);
}
