package com.hsd.controller;

import com.hsd.Code;
import com.hsd.JsonResult;
import com.hsd.PageBean;
import com.hsd.model.Evaluate;
import com.hsd.service.GoodsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName GoodsController
 * @Description: TODO
 * @Author:
 */
@CrossOrigin
@RestController
public class GoodsController {

    @Resource
    private GoodsService goodsService;


    /**
     * 根据商品ID查询商品评价信息
     * @param pageNo          第几页
     * @param pageSize        一页显示几个
     * @param evaluationLevel 评价等级 A好评 B中评 C差评
     * @param goodsId         商品id
     * @return {@link Object} 查询到的评价信息
     */
    @RequestMapping("/selectEvaluateByGoodsId")
    public Object selectEvaluate(Long pageNo,Long pageSize,String evaluationLevel,String goodsId){
        //根据商品id查询出商品评价信息 好评 中评 差评
        PageBean<List<Evaluate>> listPageBean = goodsService.selectEvaluateByGoodsId(pageNo,pageSize,evaluationLevel,goodsId);
        return new JsonResult<Object>(Code.OK,"",listPageBean);
    }
}
