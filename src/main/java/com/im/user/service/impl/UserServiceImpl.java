package com.im.user.service.impl;

import com.im.user.entity.User;
import com.im.user.repository.UserMapper;
import com.im.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author Xiong Hao
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUser(String mobile) {
        User user = new User();
        user.setMobile(mobile);
        return userMapper.selectOne(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void createUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void initAccount(User user) {
        user.setAccid(UUID.randomUUID().toString().replace("-", ""));
        userMapper.insert(user);
        //调用云信接口注册
        //update
        user.setAccid("accid1");
        user.setToken("token");
        userMapper.updateById(user);
    }
}
