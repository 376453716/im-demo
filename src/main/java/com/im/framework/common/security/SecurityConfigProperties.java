package com.im.framework.common.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Xiong Hao
 */
@Data
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfigProperties {

    private String ignorePath;
}
