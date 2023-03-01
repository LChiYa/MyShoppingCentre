package com.hsd.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hsd.mapper.UserMapper;
import com.hsd.model.User;
import com.hsd.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
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

    /**
     * 认证字段是否标准
     * @param user 要注册的用户
     * @param confirmPassword 确认密码
     * @return 0表示电话号码为空 1表示密码为空 2表示两次密码不一致 3表示电话号码长度错误 4表示要注册的用户已经存在 5表示注册成功
     */
    @Override
    public int enrollUser(User user, String confirmPassword) {
        //进入if表示电话号码为空
        if (user.getPhone().trim().equals("")){
            return 0;
        }
        //进入if表示密码不能为空
        if (user.getPassword().trim().equals("")){
            return 1;
        }
        //进入if表示两次密码不一致
        if (!user.getPassword().trim().equals(confirmPassword.trim())){
            return 2;
        }
        //进入if表示电话号码长度错误
        if (user.getPhone().trim().length() != 11){
            return 3;
        }
        //电话号码有唯一约束性 所以要是有重复的数据插入 会报错 DuplicateKeyException
        try {
            //向数据库插入(注册)用户
            userMapper.insertSelective(user);
        } catch (DuplicateKeyException e) {
            return 4;
        }
        //到这里表示注册成功
        return 5;
    }
}
