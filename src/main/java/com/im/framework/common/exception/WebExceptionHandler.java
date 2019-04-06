package com.im.framework.common.exception;

import com.im.framework.common.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Xiong Hao
 */
@ControllerAdvice
@Slf4j
public class WebExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    public ResponseResult exception(BizException exception) {
        log.error("", exception);
        return new ResponseResult(exception.getCode(), exception.getMsg());
    }

    @ExceptionHandler
    public ResponseResult exception(Exception exception) {
        log.error("", exception);
        return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),"system error:" + exception.getMessage());
    }
}