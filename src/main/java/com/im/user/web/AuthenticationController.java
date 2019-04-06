package com.im.user.web;

import com.im.framework.common.result.ResponseResult;
import com.im.framework.common.security.JwtUser;
import com.im.framework.common.security.auth.JwtAuthenticationRequest;
import com.im.framework.common.security.auth.TokenBasedAuthentication;
import com.im.framework.common.security.auth.TokenHelper;
import com.im.user.entity.User;
import com.im.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(value = "用户登陆接口", tags = "login")
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    private UserService userService;

    @ApiOperation("登陆获取token")
    @PostMapping(value = "/login")
    public ResponseResult<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        // Perform the security
        //检查验证码 todo
        if (!authenticationRequest.getVerifycode().equals("123456")) {
            throw new RuntimeException("验证码错误");
        }
        User user = userService.queryUser(authenticationRequest.getMobile());
        //查询用户是否注册，
        if (user == null || StringUtils.isBlank(user.getToken())) {
            user = new User();
            user.setMobile(authenticationRequest.getMobile());
            userService.initAccount(user);
        }
        JwtUser jwtUser = new JwtUser();
        jwtUser.setId(user.getId());
        jwtUser.setAccId(user.getAccid());
        jwtUser.setMobile(user.getMobile());
        jwtUser.setImToken(user.getToken());
        Authentication authentication = new TokenBasedAuthentication(jwtUser);
        // Inject into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jws = tokenHelper.generateToken(jwtUser);
        jwtUser.setAuthToken(jws);
        // Return the token
        return new ResponseResult(jwtUser);
    }

    @ApiOperation("刷新token过期时间")
    @PostMapping(value = "/refresh")
    public ResponseResult<?> refreshAuthenticationToken(HttpServletRequest request, Principal principal) {
        String authToken = tokenHelper.getToken(request);
        if (authToken != null && principal != null) {
            String refreshedToken = tokenHelper.refreshToken(authToken);
            return new ResponseResult(refreshedToken);
        } else {
            throw new AuthenticationCredentialsNotFoundException("invalid authToken");
        }
    }

}