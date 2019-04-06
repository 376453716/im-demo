package com.im.user.web;

import com.im.framework.common.result.ResponseResult;
import com.im.user.entity.User;
import com.im.user.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiong Hao
 */
@Api("用户接口")
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public ResponseResult<User> getUserInfo() {
        return new ResponseResult<>(userService.queryUser(""));
    }
}
