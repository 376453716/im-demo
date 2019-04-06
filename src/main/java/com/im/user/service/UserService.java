package com.im.user.service;

import com.im.user.entity.User;

/**
 * @author Xiong Hao
 */
public interface UserService {

    User queryUser(String mobile);

    void updateUser(User user);

    void createUser(User user);

    void initAccount(User user);
}
