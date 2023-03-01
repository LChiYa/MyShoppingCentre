package com.hsd.controller;

import com.hsd.Code;
import com.hsd.JsonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName GoodsController
 * @Description: TODO
 * @Author:
 */
@CrossOrigin
@RestController
public class GoodsController {

    @RequestMapping("/selectEvaluate")
    public Object selectEvaluate(){

        return new JsonResult<Object>(Code.OK,"");
    }
}
