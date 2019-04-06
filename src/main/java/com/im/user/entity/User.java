package com.im.user.entity;

import lombok.Data;

/**
 * @author Xiong Hao
 */
@Data
public class User {

    private Long id;

    /**
     * 用户云信帐号
     */
    private String accid;

    /**
     * 用户云信token
     */
    String token;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户头像
     */
    private String icon;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户生日
     */
    private String birth;

    /**
     * 用户昵性别
     */
    private Integer gender;

    /**
     * 扩展信息
     */
    private String ex;

}
