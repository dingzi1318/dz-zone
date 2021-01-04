package com.user.enums;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * 返回结果状态码枚举
 *
 * @author dingzi
 */
public enum ResultCode implements CommonResultCode {

    /**
     * 0-成功
     */
    SUCCESS(0, "请求成功"),

    /**
     * -1-失败
     */
    FAIL(-1, "请求失败"),

    /**
     * 参数非法
     */
    INVALID_PARAM(-10001, "参数非法"),

    /**
     * 未登录认证
     */
    UNAUTHENTICATED(-10002, "未登录认证"),

    /**
     * 重复提交
     */
    REPEAT_REQUEST(-10003, "重复提交"),


    SYSTEM_EXCEPTION(-99999, "系统异常")
    ;

    private final int code;

    private final String message;


    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
