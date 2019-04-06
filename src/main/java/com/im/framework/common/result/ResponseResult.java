package com.im.framework.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiong Hao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResponseResult(T data) {
        this.data = data;
        this.code = Constants.SUCCESS_CODE;
        this.msg = "success";
    }

    public ResponseResult(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }


}
