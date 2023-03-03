package com.hsd.service.remInterface;

import com.hsd.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @ClassName reduceInventory
 * @Description: TODO
 * @Author:
 */
@FeignClient("GoodsService")
public interface reduceInventory {
    @GetMapping("/reduceInventory")
    JsonResult<Map<String,Object>> subtractStory(@RequestParam Long goodsId, @RequestParam Integer buyNum);
}
