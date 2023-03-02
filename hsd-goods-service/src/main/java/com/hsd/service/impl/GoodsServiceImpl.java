package com.hsd.service.impl;

import com.hsd.PageBean;
import com.hsd.mapper.EvaluateMapper;
import com.hsd.model.Evaluate;
import com.hsd.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName GoodsServiceImpl
 * @Description: TODO
 * @Author:
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private EvaluateMapper evaluateMapper;


    @Override
        public PageBean<List<Evaluate>> selectEvaluateByGoodsId(Long pageNo, Long pageSize, String evaluationLevel, String goodsId) {
        //创建PageBean对象
        PageBean pageBean = new PageBean<>(pageNo,pageSize);
        //先根据商品id查询查询出商品评价一共与多少条评价
        Long totalNum = evaluateMapper.countEvaluateInfo(goodsId,evaluationLevel);
        //根据总评价信息计算出有几页
        pageBean.setTotalNum(totalNum);
        //查询评价信息
        List<Evaluate> listPageBean = evaluateMapper.findAllProductReviewsByGoodsId(pageBean.getSkipNum(),pageBean.getPageSize(),evaluationLevel,goodsId);
        //设置PageBean中的data
        pageBean.setData(listPageBean);
        return pageBean;
    }
}
