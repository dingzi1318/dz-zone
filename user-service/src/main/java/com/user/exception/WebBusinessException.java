package com.user.exception;

import com.user.enums.ResultCode;

/**
 * 自定义业务异常
 *
 * @author dingzi
 */
public class WebBusinessException extends RuntimeException {

    /** 状态码*/
    private int code;

    /**
     * 默认的业务异常
     */
    public WebBusinessException() {
    }

    public WebBusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public WebBusinessException(ResultCode codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }


    public WebBusinessException(String message) {
        super(message);
    }

    public WebBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebBusinessException(Throwable cause) {
        super(cause);
    }

    public WebBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getCode() {
        return code;
    }


}
