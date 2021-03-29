package com.user.handler;

import com.alibaba.fastjson.JSON;
import com.user.dto.ApiResult;
import com.user.enums.ResultCode;
import com.user.exception.WebBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ApiResult validateExceptionHandler(ConstraintViolationException exception) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        String message = constraintViolations.stream().map(ConstraintViolation::getMessage).findFirst().orElse("系统异常");
        return ApiResult.fail(message);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMsg = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst().orElse("参数异常");
        return ApiResult.fail(errorMsg);
    }

    @ExceptionHandler(BindException.class)
    public ApiResult bindExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
        return ApiResult.fail(defaultMessage);
    }


}
