package com.user.exception;

/**
 * rpc业务异常
 *
 * @author dingzi
 */
public class RpcBusinessException extends RuntimeException {

    private int code;

    public RpcBusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public RpcBusinessException() {
        super();
    }

    public RpcBusinessException(String message) {
        super(message);
    }

    public RpcBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcBusinessException(Throwable cause) {
        super(cause);
    }

    protected RpcBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
