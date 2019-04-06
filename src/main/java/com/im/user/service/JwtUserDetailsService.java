package com.im.user.service;

import com.im.framework.common.security.JwtUser;
import com.im.user.entity.User;
import com.im.user.repository.UserMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserMapper userMapper;

    @Override
    public JwtUser loadUserByUsername(String mobile) throws UsernameNotFoundException {
        User queryUser  = new User();
        queryUser.setMobile(mobile);
        User user = userMapper.selectOne(queryUser);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with mobile '%s'.", mobile));
        } else {
            JwtUser jwtUser = new JwtUser();
            jwtUser.setImToken(user.getToken());
            jwtUser.setMobile(user.getMobile());
            jwtUser.setAccId(user.getAccid());
            jwtUser.setId(user.getId());
            return jwtUser;
        }
    }

}
