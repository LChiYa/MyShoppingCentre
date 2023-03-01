package com.hsd.service;

import com.hsd.model.User;

/**
 * @ClassName UserService
 * @Description: TODO
 * @Author:
 */
public interface UserService {
    int selectUserByPhone(User user);

    int enrollUser(User user, String confirmPassword);
}
