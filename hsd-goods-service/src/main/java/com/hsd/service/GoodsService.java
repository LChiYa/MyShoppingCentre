package com.hsd.service;

import com.hsd.PageBean;
import com.hsd.model.Evaluate;

import java.util.List;

/**
 * @ClassName GoodsService
 * @Description: TODO
 * @Author:
 */
public interface GoodsService {
    PageBean<List<Evaluate>> selectEvaluateByGoodsId(Long pageNo, Long pageSize, String evaluationLevel, String goodsId);
}
