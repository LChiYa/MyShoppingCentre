package com.hsd.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hsd.mapper.UserMapper;
import com.hsd.model.User;
import com.hsd.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName UserServiceImpl
 * @Description: TODO
 * @Author:
 */
@Service
public class UserServiceImpl implements UserService {

    //注入 UserMapper
    @Resource
    private UserMapper userMapper;
    /**
     * 根据用户输入的电话号码查询用户登录
     * @param user 要查询的用户
     * @return  0表示电话号码为空 1表示电话号码的长度错误 2表示密码为空 3表示用户名或密码错误 4表示此用户不存在 5表示登录成功
     */
    @Override
    public int selectUserByPhone(User user) {
        //进入if表示电话号码为空 并返回0
        if (user.getPhone().trim().equals("")){
            return 0;
        }
        //进入if表示电话号码长度错误 并返回1
        if (user.getPhone().trim().length() != 11){
            return 1;
        }
        //进入if表示密码为空 并返回2
        if (user.getPassword().trim().equals("")){
            return 2;
        }
        //根据电话号码查询用户信息 并返回用户的信息
        User u = userMapper.selectUserByPhone(user.getPhone());
        //进入if表示用户不存在
        if (u == null){
            return 3;
        }
        //进入if表示的电话号码或密码错误 并返回3
        if (!user.getPassword().trim().equals(u.getPassword().trim())){
            return 4;
        }


        //将数据库查询到的数据拷贝到user中
        BeanUtils.copyProperties(u,user);
        //登录成功
        return 5;
    }
}
