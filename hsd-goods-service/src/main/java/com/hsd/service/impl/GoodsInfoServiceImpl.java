package com.hsd.service.impl;

import com.hsd.mapper.GoodsInfoMapper;
import com.hsd.service.GoodsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName GoodsInfoServiceImpl
 * @Description: TODO
 * @Author:
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Resource
    private GoodsInfoMapper goodsInfoMapper;

    /**
     * 减少库存
     * @param goodsId 商品id
     * @param buyNum  购买数量
     * @return int 大于 1 表示减少库存成功
     */
    @Override
    public int reduceInventory(Long goodsId, Integer buyNum) {
        //根据商品id减少库存
        return goodsInfoMapper.reduceInventory(goodsId,buyNum);
    }
}
