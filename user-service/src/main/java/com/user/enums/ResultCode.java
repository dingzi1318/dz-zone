package com.user.enums;

/**
 * 返回结果状态码枚举
 *
 * @author dingzi
 */
public enum ResultCodeEnum {

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

    REPEAT_REQUEST(-10003, "重复提交")
    ;

    private final int code;

    private final String message;


    ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
