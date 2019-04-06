package com.im.framework.common.result;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Xiong Hao
 */
@Data
public class Constants {

    /**
     * 成功
     */
    public static final Integer SUCCESS_CODE = HttpStatus.OK.value();

    /**
     * 未认证
     */
    public static final Integer UNAUTHORIZED = HttpStatus.UNAUTHORIZED.value();

    /**
     * 未授权
     */
    public static final Integer FORBIDDEN = HttpStatus.FORBIDDEN.value();


}
