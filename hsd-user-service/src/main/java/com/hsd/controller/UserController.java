package com.hsd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsd.Code;
import com.hsd.JsonResult;
import com.hsd.mapper.UserMapper;
import com.hsd.model.User;
import com.hsd.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.*;

/**
 * @ClassName UserController
 * @Description: TODO
 * @Author:
 */
@RestController
//跨域响应
@CrossOrigin
public class UserController {

    //注入 UserService
    @Resource
    private UserService userService;

    //注入 StringRedisTemplate
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //注入 UserMapper
    @Resource
    private UserMapper userMapper;


    /**
     * 验证用户登录
     *
     * @param user 要验证的用户
     * @return 登录成功/失败
     */
    @PostMapping("/logIn")
    public Object logIn(User user) {
        //获取用户登录错误的次数
        String logIn = stringRedisTemplate.opsForValue().get("logInErr:" + user.getPhone());
        //进入if表示logIn不为空或者大于1
        int l = 5;
        if (logIn != null && Long.parseLong(logIn) >= l) {
            return new JsonResult<Object>(Code.ERROR, "此账号今天已锁定,请明天再试");
        }
        //0表示电话号码为空
        //1表示电话号码的长度错误
        //2表示密码为空
        //3,4表示用户名或密码错误
        //5表示登录成功
        int res = userService.selectUserByPhone(user);
        //向数据库查询用户是否存在
        User users = userMapper.selectUserByPhone(user.getPhone());
        switch (res) {
            case 0:
                return new JsonResult<Object>(Code.ERROR, "电话号码不能为空");
            case 1:
                return new JsonResult<Object>(Code.ERROR, "电话号码长度错误");
            case 2:
                return new JsonResult<Object>(Code.ERROR, "密码不能为空");
            case 3:
            case 4:
                //进入if表示数据库不存在此电话号码
                if (users != null) {
                    //限制用户登录错误次数
                    logInErrTime(users.getPhone());
                    Long a = stringRedisTemplate.opsForValue().increment("logInErr:" + users.getPhone());
                    //限制用户登录错误的次数为5次
                    Long b = 6L;
                    //每次可以登录的次数减一
                    long c = b - a;
                    return new JsonResult<Object>(Code.ERROR, "用户名或密码错误,你还有" + c + "次密码输入错误的机会");
                }

        }

        //获取随机字符串
        String token = UUID.randomUUID().toString().replace("-", "");
        //设置用户登录有效时间为60分钟
        Duration duration = Duration.ofSeconds(60 * 60);
        //登录成功之前将登录成功的用户信息以JSON字符串的方式存入Redis中
        stringRedisTemplate.opsForValue().set("UserToken:" + token, JSON.toJSONString(user), duration);
        //为了节省Redis的资源 在登录成功之前将登录错误次数删除
        if (users != null) {
            stringRedisTemplate.delete("logInErr:" + users.getPhone());
        }
        //将token和用户信息存到Map集合中
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        assert users != null;
        map.put("phone",users.getPhone());
        map.put("nickName",users.getNickName());
        map.put("id",users.getId());
        //登录成功
        return new JsonResult<Object>(Code.OK, "登录成功", map);
    }

    /**
     * 设置用户登录错误的时间 为明天0点0分0面减去当前时间
     *
     * @param phone 登录错误的电话号码
     */
    private void logInErrTime(String phone) {
        Calendar calendar = Calendar.getInstance();
        //设置秒为0
        calendar.set(Calendar.SECOND, 0);
        //设置分钟为0
        calendar.set(Calendar.MINUTE, 0);
        //设置小时为0
        calendar.set(Calendar.HOUR, 0);
        //在当前天数加1天
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        //将时间单位毫秒转换成秒
        Duration duration = Duration.ofSeconds(calendar.getTimeInMillis() / 1000 - System.currentTimeMillis() / 1000);
        //设置超时时间 单位是秒
        stringRedisTemplate.expire("logInErr:" + phone, duration);
    }

    /**
     * 注册请求
     * @param user 要注册的用户
     * @param confirmPassword 确认密码
     * @return 注册成功/失败
     */
    @PostMapping("/enroll")
    public Object enroll(User user,String confirmPassword){
        //认证用户的各项字段是否符合标准 并返回
        //0表示电话号码为空
        //1表示密码为空
        //2表示两次密码不一致
        //3表示电话号码长度错误
        //4表示要注册的用户已经存在
        int num = userService.enrollUser(user,confirmPassword);
        switch (num){
            case 0:
                 return new JsonResult<Object>(Code.ERROR, "电话号码不能为空");
            case 1:
                 return new JsonResult<Object>(Code.ERROR, "密码不能为空");
            case 2:
                 return new JsonResult<Object>(Code.ERROR, "两次密码不一致");
            case 3:
                 return new JsonResult<Object>(Code.ERROR, "电话号码长度错误");
            case 4:
                 return new JsonResult<Object>(Code.ERROR, "用户已经注册");
        }
        //注册成功
        return new JsonResult<Object>(Code.OK, "注册成功");
    }

    @GetMapping("/getUserId")
    public Object getUserId(String token){
                //获取Redis中的用户登录的状态
        String userToken = stringRedisTemplate.opsForValue().get("UserToken:" + token);
        //进入if表示用户没有登录
        if (userToken == null){
            return new JsonResult<Object>(Code.NO_LOGIN,"");
        }
        //到这里表示用户已经登录 重新设置超时时间为60分钟
        Duration duration = Duration.ofSeconds(60 * 60);
        stringRedisTemplate.expire("UserToken:" + token,duration);
        //获取用户的id
        long userId = JSONObject.parseObject(userToken).getBigInteger("id").longValue();
        //返回获取到的用户id
        return new JsonResult<Object>(Code.OK,userId);
    }
}
