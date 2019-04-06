package com.im.framework.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiong Hao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BizException extends RuntimeException {

    private Integer code;

    private String msg;

}
