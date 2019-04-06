package com.im.framework.common.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @description:
 * @create: 2018-09-17 15:46
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUser implements UserDetails {

    /**
     * 用戶ID
     */
    private Long id;

    /**
     * 用戶网易云信ID
     */
    private String accId;

     /**
     * 用戶云信token
     */
    private String imToken;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户会话认证token
     */
    private String authToken;

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return accId;
    }

    @Override
    public String getUsername() {
        return mobile;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}