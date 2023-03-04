package com.hsd;

import com.hsd.mapper.OrdersMapper;
import com.hsd.model.Orders;
import com.hsd.service.remInterface.reduceInventory;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
class HsdOrdersServiceApplicationTests {

    @Resource
    private OrdersMapper ordersMapper;
    @Test
    public void contextLoads() {
//        JsonResult<Map<String, Object>> mapJsonResult = reduceInventory.subtractStory(1011045L, 1);
//        Map<String, Object> result = mapJsonResult.getResult();
//        Object price = result.get("price");
//        Object store = result.get("store");
//        System.out.println("price = " + price);
//        System.out.println("store = " + store);
//        Orders orders = new Orders();
//        int i = ordersMapper.insertSelective(orders);
//        System.out.println("id = " + orders.getId());

    }

}
