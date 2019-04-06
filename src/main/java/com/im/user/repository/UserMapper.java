package com.im.user.repository;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.im.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper  extends BaseMapper<User> {

}