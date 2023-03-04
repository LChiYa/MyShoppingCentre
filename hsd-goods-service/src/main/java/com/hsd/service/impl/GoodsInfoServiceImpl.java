package com.hsd.service.impl;

import com.hsd.Code;
import com.hsd.JsonResult;
import com.hsd.mapper.GoodsInfoMapper;
import com.hsd.model.GoodsInfo;
import com.hsd.service.GoodsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据商品id查询商品价格和库存
     * @param goodsId 商品id
     * @return {@link Map} 存价格和库存 <{@link String}, {@link Object}>
     */
    @Override
    public List<GoodsInfo> selectGoodsPriceAndStore(Long goodsId) {
        //查询商品价格和库存 并返回List集合
       return goodsInfoMapper.selectGoodsPriceAndStore(goodsId);
    }

}
