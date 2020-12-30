package com.user.exceptions;

/**
 * 自定义业务异常
 *
 * @author dingzi
 */
public class BusinessException extends RuntimeException {

    /** 状态码*/
    private int code;

    /**
     * 默认的业务异常
     */
    public BusinessException() {
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }


    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
