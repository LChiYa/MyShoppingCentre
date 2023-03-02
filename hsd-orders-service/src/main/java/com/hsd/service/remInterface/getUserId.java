package com.hsd.service.remInterface;

import com.hsd.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName getUserId
 * @Description: TODO
 * @Author:
 */
@FeignClient("UserService")
public interface getUserId {
    @GetMapping("/getUserId")
    JsonResult<Object> getId(@RequestParam String token);
}
