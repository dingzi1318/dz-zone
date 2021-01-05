package com.user.handler;

import com.alibaba.fastjson.JSON;
import com.user.dto.ApiResult;
import com.user.enums.ResultCode;
import com.user.exception.WebBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常统一处理器
 *
 * @author dingzi
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义业务异常处理
     */
    @ExceptionHandler(value = WebBusinessException.class)
    public ApiResult bizExceptionHandler(HttpServletRequest request, WebBusinessException e) {
        log.error("发生业务异常，request url:{}, param:{}, cause:{}",
                request.getRequestURL(), JSON.toJSON(request.getParameterMap()), e.getMessage());
        return ApiResult.fail(e.getCode(), e.getMessage());
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ApiResult nullPointExceptionHandler(HttpServletRequest request, Exception e) {
        log.error("发生未知的异常了，request url:{}, param:{}",
                request.getRequestURL(), JSON.toJSON(request.getParameterMap()), e);
        return ApiResult.fail(ResultCode.SYSTEM_EXCEPTION);
    }

}
