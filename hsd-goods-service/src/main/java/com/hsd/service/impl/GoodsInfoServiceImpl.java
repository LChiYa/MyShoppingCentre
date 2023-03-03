package com.hsd.service.impl;

import com.hsd.mapper.GoodsInfoMapper;
import com.hsd.service.GoodsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
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
    public Map<String, Object> selectGoodsPriceAndStore(Long goodsId) {
        //创建Map集合
        HashMap<String, Object> map = new HashMap<>();
        //查询商品价格
        BigDecimal price = goodsInfoMapper.selectGoodsPrice(goodsId);
        //查询商品库存
        Integer store = goodsInfoMapper.selectGoodsStore(goodsId);
        //进入if表示价格或者库存没有查到
        if (price == null || store == null){
            map.put("error","价格或者库存没有查到,请检查数据库是否存在数据");
            return map;
        }
        //到这里表示查询到价格和库存 将价格和库存存入Map集合中
        map.put("price",price);
        map.put("store",store);
        return map;
    }

}
