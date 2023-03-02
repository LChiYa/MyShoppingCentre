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


    /**
     * 根据商品ID查询商品评价信息
     * @param pageNo          第几页
     * @param pageSize        一页显示几个
     * @param evaluationLevel 评价等级 A好评 B中评 C差评
     * @param goodsId         商品id
     * @return {@link PageBean}<{@link List}<{@link Evaluate}>> 查询到的商品评价信息
     */
    @Override
        public PageBean<List<Evaluate>> selectEvaluateByGoodsId(Long pageNo, Long pageSize, String evaluationLevel, String goodsId) {
        //创建PageBean对象
        PageBean pageBean = new PageBean<>(pageNo,pageSize);
        if (evaluationLevel.equals("img")) {
            //先根据商品id查询查询出有图商品评价一共与多少条评价
            Long totalNum = evaluateMapper.countEvaluateInfoImg(goodsId);
            //根据总评价信息计算出有几页
            pageBean.setTotalNum(totalNum);
            //查询有图评价信息
            List<Evaluate> listPageBean = evaluateMapper.findAllProductReviewsByGoodsIdImg(pageBean.getSkipNum(),pageBean.getPageSize(),goodsId);
            //将查询到的数据设置到PageBean的data中并返回
            pageBean.setData(listPageBean);
        } else {
            //先根据商品id查询查询出商品评价一共与多少条评价
            Long totalNum = evaluateMapper.countEvaluateInfo(goodsId,evaluationLevel);
            //根据总评价信息计算出有几页
            pageBean.setTotalNum(totalNum);
            //查询评价信息
            List<Evaluate> listPageBean = evaluateMapper.findAllProductReviewsByGoodsId(pageBean.getSkipNum(),pageBean.getPageSize(),evaluationLevel,goodsId);
            //将查询到的数据设置到PageBean的data中并返回
            pageBean.setData(listPageBean);
        }
        return pageBean;
    }
}
